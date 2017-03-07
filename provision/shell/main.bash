#!/bin/sh

# Directory in which librarian-puppet should manage its modules directory
PUPPET_DIR=/etc/puppet/

$(which git > /dev/null 2>&1)
FOUND_GIT=$?
$(which librarian-puppet > /dev/null 2>&1)
FOUND_LP=$?

apt-get -q -y update

# Make sure Git is installed
if [ "$FOUND_GIT" -ne '0' ]; then
  echo 'Attempting to install Git.'
  apt-get -q -y install git
  echo 'Git installed.'
fi

# Make sure librarian-puppet is installed
if [ "$FOUND_LP" -ne '0' ]; then
  apt-get install -y ruby1.9.1-dev git make
  apt-get -q -y install ruby-json
  gem install librarian-puppet -v 2.2.3
fi

if [ ! -d "$PUPPET_DIR" ]; then
  mkdir -p $PUPPET_DIR
fi

cp /vagrant/provision/puppet/Puppetfile $PUPPET_DIR

if [ "$(gem search -i librarian-puppet)" = "false" ]; then
  gem install librarian-puppet -v 2.2.3
  cd $PUPPET_DIR && librarian-puppet install
else
  cd $PUPPET_DIR && librarian-puppet update
fi
