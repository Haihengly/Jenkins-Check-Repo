def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service/${config.FOLDER}-${config.envName}"

    dir("${env.WORKSPACE}/.scm-detect") {
      checkout([$class: 'GitSCM',
        branches: [[name: config.branch]],
        userRemoteConfigs: [[url: config.REPO_URL]]
      ])
    }

    sh """
      rm -rf ${STORE_DIR}
      mkdir -p ${STORE_DIR}
      cp -r ${env.WORKSPACE}/.scm-detect ${STORE_DIR}
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
