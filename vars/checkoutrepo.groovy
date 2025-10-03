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
      scp -r ${TMP_DIR} jenkins@34.87.120.95:${STORE_DIR}/
      ssh jenkins@34.87.120.95 '
        cd ${STORE_DIR}/${config.FOLDER}-${config.envName}
        docker compose -f docker-compose.base.yml -f docker-compose.${config.envName}.yml --env-file .env.dev build
        docker compose -f docker-compose.base.yml -f docker-compose.${config.envName}.yml --env-file .env.dev up -d
      '
    """
}


    // sh """
    //     rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
    //     pwd
    //     ls -la ${CLONE_DIR}/
    //     git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
    //     ls -la ${CLONE_DIR}/${config.BUILD_DIR}
    // """
