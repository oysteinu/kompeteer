Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty32"
  config.vm.hostname = "kompeteer.box"
  config.vm.synced_folder "./", "/vagrant", id: "vagrant-root", type: "nfs"

  config.vm.provider :virtualbox do |config, override|
    ### Change network card to PCnet-FAST III
    # For NAT adapter
    config.customize ["modifyvm", :id, "--nictype1", "Am79C973"]
    # For host-only adapter
    config.customize ["modifyvm", :id, "--nictype2", "Am79C973"]

    config.customize ["setextradata", :id, "VBoxInternal2/SharedFoldersEnableSymlinksCreate/v-root", "1"]

    override.vm.network "forwarded_port", guest: 8080, host: 8080
  end

  config.puppet_install.puppet_version = "3.8.1"

  config.vm.provision :shell, :path => "provision/shell/main.sh"

  config.vm.provision "puppet" do |puppet|
    puppet.manifests_path = 'provision/puppet/manifests'
    puppet.manifest_file = 'default.pp'
    puppet.module_path = [ 'provision/puppet/modules' ]
    puppet.options = "--verbose --debug"
  end
end
