def call(Map config) {
    def CLONE_DIR = "/My-Docker/Dev-Service"


    dir("${env.WORKSPACE}/.scm-detect") {
      checkout([$class: 'GitSCM',
        branches: [[name: config.branch]],
        userRemoteConfigs: [[url: config.REPO_URL]]
      ])
    }
    sh """
      rm -rf ${CLONE_DIR}/${config.BUILD_DIR}
      cp -r ${env.WORKSPACE}/.scm-detect ${CLONE_DIR}/${config.BUILD_DIR}
      ls ${env.WORKSPACE}/.scm-detect
    """

    // sh """
    //     rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
    //     pwd
    //     ls -la ${CLONE_DIR}/
    //     git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
    //     ls -la ${CLONE_DIR}/${config.BUILD_DIR}
    // """

}
