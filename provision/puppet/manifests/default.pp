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

class { 'java' : 
  distribution  => 'jdk',
  package       => 'java-1.8.0-openjdk-devel',
}

package { 'maven':
	require  => Class['java'],
}

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

package { 'yarn':
  provider => 'npm',
  require  => Class['nodejs'],
}