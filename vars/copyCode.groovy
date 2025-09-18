def call(Map config) {
    def CLONE_DIR = "${env.HOME}/code-clone"
    def STORE_DIR = "${env.HOME}/My-Docker"

    // Copy code into Docker context
    sh "cp -r ${CLONE_DIR}/my-app ${STORE_DIR}/Dev-Service/"
}
