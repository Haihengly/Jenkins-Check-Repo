def call() {
    sh """
      ssh jenkins@34.87.120.95
      whoami 
      cd /
      ls -l
    """
}
