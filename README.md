# Required Vagrant plugins

```
vagrant plugin install vagrant-librarian-puppet (https://github.com/mhahn/vagrant-librarian-puppet)
vagrant plugin install vagrant-puppet-install (https://github.com/petems/vagrant-puppet-install)
```

* vagrant up
  * Installs Puppet, Git, Java, Node.js, NPM, Yeoman
* Add NPM libraries to PATH
 
  ```
  export PATH=/usr/local/node/node-v6.10.0/bin:$PATH
  ```
 
