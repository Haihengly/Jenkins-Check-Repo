def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service"
    def TMP_DIR = "/tmp/${config.FOLDER}-${config.envName}"

    // Checkout repo in Jenkins container
    dir("${env.WORKSPACE}/.scm-detect") {
      checkout([$class: 'GitSCM',
        branches: [[name: config.branch]],
        userRemoteConfigs: [[url: config.REPO_URL]]
      ])
    }

    // Copy files to a temp folder in Jenkins container
    sh """
      rm -rf ${TMP_DIR}
      mkdir -p ${TMP_DIR}
      cp -r ${env.WORKSPACE}/.scm-detect/* ${TMP_DIR}
    """

    // Transfer files to remote server and build/deploy
    sh """
      scp -r ${TMP_DIR} jenkins@vm6:${STORE_DIR}/
    """
}
