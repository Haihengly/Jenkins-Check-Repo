def call(Map config) {
    def CLONE_DIR = "${env.HOME}/code-clone/"
    def STORE_DIR = "${env.HOME}/My-Docker/"

    // Copy code into Docker context
    sh "rsync -a ${CLONE_DIR}/${config.REPOSITORY_NAME}/ ${STORE_DIR}/${config.BUILD_DIR}/"

}
 