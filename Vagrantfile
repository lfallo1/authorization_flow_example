# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.box = "bento/ubuntu-16.04"

  #ssh
  config.vm.network "forwarded_port", guest: 22, host: 22422

  #auth server
  config.vm.network "forwarded_port", guest: 9000, host: 9000

  #resource server
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  #resource server debug
  config.vm.network "forwarded_port", guest: 8000, host: 8000

  # config.vm.synced_folder "../data", "/vagrant_data"

  # shell provisioning
  config.vm.provision "shell", inline: <<-SHELL
    apt-get update
    apt-get install -y puppet
    puppet module install --force puppetlabs/stdlib
  SHELL

  #puppet provisioning
  config.vm.provision :puppet do |puppet|
    puppet.manifests_path = "puppet/manifests"
    puppet.manifest_file  = "oauth.pp"
  end

end
