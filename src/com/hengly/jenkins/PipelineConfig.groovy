// package com.hengly.jenkins

// class PipelineConfig {
//     String envName = 'Staging'
//     String version = '1.0.0'
//     boolean deploy = true
//     boolean runTests = true

//     String branch
//     String repoUrl 
// }

package com.hengly.jenkins
 
class PipelineConfig implements Serializable {
    String envName = 'Staging'
    String version = '1.0.0'
    boolean deploy = false
    boolean runTests = true
    String branch = 'main'
    String repoUrl = ''

    PipelineConfig(Map params = [:]) {
        envName   = params.get('envName', envName)
        version   = params.get('version', version)
        deploy    = params.get('deploy', deploy)
        runTests  = params.get('runTests', runTests)
        branch    = params.get('branch', branch)
        repoUrl   = params.get('repoUrl', repoUrl)
    }

    Map toMap() {
        return [
            envName: envName,
            version: version,
            deploy: deploy,
            runTests: runTests,
            branch: branch,
            repoUrl: repoUrl
        ]
    }
}
