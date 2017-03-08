Exec
{
	path => ["/usr/bin", "/bin", "/usr/sbin", "/sbin", "/usr/local/bin", "/usr/local/sbin"]
}

exec
{
    'apt-get update':
        command => '/usr/bin/apt-get update'
}

group { "puppet":
	ensure => "present",
}

case $::operatingsystem {
	ubuntu: {
	  include apt

	  apt::ppa { 'ppa:openjdk-r/ppa': 
		ensure => present,
	  }

	  exec { 'apt-update':
		command => '/usr/bin/apt-get update',
		require => [
		  Apt::Ppa['ppa:openjdk-r/ppa']
		],
	  }

	  package { 'openjdk-8-jdk':
		require  => [
		  Exec['apt-update'],
		  Apt::Ppa['ppa:openjdk-r/ppa'],
		],
	  }
	}

	default: {
	  notice "Unsupported operatingsystem ${::operatingsystem}"
	}
}

/*
package { 'maven':
	require  => Class['java'],
}
*/

class { 'nodejs':
  version => 'lts',
  target_dir => '/bin',
}

package { 'yo':
  provider => 'npm',
  require  => Class['nodejs'],
}

package { 'generator-jhipster':
  provider => 'npm',
  require  => Class['nodejs'],
}

package { 'bower':
  provider => 'npm',
  require  => Class['nodejs'],
}

package { 'gulp-cli':
  provider => 'npm',
  require  => Class['nodejs'],
}

package { 'yarn':
  provider => 'npm',
  require  => Class['nodejs'],
}