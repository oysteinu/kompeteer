* vagrant up
  * Installs Puppet, Git, Java, Node.js
* Install Yarn according to instructions (https://yarnpkg.com/en/docs/install#linux-tab)
 
  ```
  curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
  echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
  ```
 
  ```
  sudo apt-get update && sudo apt-get install yarn`
  ```

* Install Yeoman
  * `sudo yarn global add yo`
* Install JHipster
  * `sudo yarn global add generator-jhipster`
