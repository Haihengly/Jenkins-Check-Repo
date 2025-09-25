// def call(Map config) {
//     def CLONE_DIR = "/My-Docker/Dev-Service"

//     sh """
//         rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
//         pwd
//         ls -la ${CLONE_DIR}/
//         git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
//         ls -la ${CLONE_DIR}/${config.BUILD_DIR}
//     """
// }


def call(Map config) {
    def CLONE_DIR = "/My-Docker/Dev-Service"

    // Clone the repo
    sh """
        rm -rf ${CLONE_DIR}/${config.BUILD_DIR}
        mkdir -p ${CLONE_DIR}
        git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
    """

    // Capture the latest commit message
    def lastCommitMsg = sh(
        script: "cd ${CLONE_DIR}/${config.BUILD_DIR} && git log -1 --pretty=format:'%s'",
        returnStdout: true
    ).trim()

    echo "Latest commit message: ${lastCommitMsg}"
    return lastCommitMsg
}
