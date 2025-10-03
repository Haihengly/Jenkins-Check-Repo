def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service"

    dir("${env.WORKSPACE}/.scm-detect") {
      checkout([$class: 'GitSCM',
        branches: [[name: config.branch]],
        userRemoteConfigs: [[url: config.REPO_URL]]
      ])
    }

    sh """
      ssh jenkins@34.87.120.95
      rm -rf ${STORE_DIR}/${config.FOLDER}-${config.envName}
      mkdir -p ${STORE_DIR}/${config.FOLDER}-${config.envName}
      cp -r ${env.WORKSPACE}/.scm-detect/* ${STORE_DIR}/${config.FOLDER}-${config.envName}
      ls ${env.WORKSPACE}/.scm-detect
    """

}

    // sh """
    //     rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
    //     pwd
    //     ls -la ${CLONE_DIR}/
    //     git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
    //     ls -la ${CLONE_DIR}/${config.BUILD_DIR}
    // """
