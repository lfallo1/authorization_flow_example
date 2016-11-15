node default {

  # misc
  package { [
      'git',
      'vim',
      'rsync',
      'ntp',
      'man',
      'nmap',
      'netcat',
      'wget',
      'dos2unix',
      'openjdk-8-jdk-headless',
      'maven'
    ]:
    ensure    => present,
  }
}
