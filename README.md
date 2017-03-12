# Required Vagrant plugins

```
vagrant plugin install vagrant-librarian-puppet (https://github.com/mhahn/vagrant-librarian-puppet)
vagrant plugin install vagrant-puppet-install (https://github.com/petems/vagrant-puppet-install)
```
# Set up environment

* Install Puppet, Git, Java, Maven, Node.js, NPM, Yeoman, JHipster using Vagrant and Puppet

  **In Windows, make sure to run the following command in a Shell with admin-rights!**

  ```
  vagrant up
  ```

* Add NPM libraries to PATH
 
  ```
  export PATH=/usr/local/node/node-v6.10.0/bin:$PATH
  ```
 
