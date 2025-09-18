def call(Map config) {
    def CLONE_DIR = "${env.HOME}/code-clone/"

    // Clean old code
    sh "rm -rf ${CLONE_DIR}"

    // Clone latest repo
    sh "git clone ${config.repoUrl} ${CLONE_DIR}"
    sh "ls -l ${CLONE_DIR}"



    // git (
    //     branch: config.branch, 
    //     url: config.repoUrl, 
    //     credentialsId: 'git_token'
    // )
}
 