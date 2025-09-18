def call(Map config) {
    def CLONE_DIR = "/home/amhengly/code-clone/"
    def STORE_DIR = "/home/amhengly/My-Docker/"

    // Copy code into Docker context
    sh "rsync -a ${CLONE_DIR}/${config.REPOSITORY_NAME}/ ${STORE_DIR}/${config.BUILD_DIR}/"

}
 