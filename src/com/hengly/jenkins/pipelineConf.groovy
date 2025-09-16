package com.hengly.jenkins

class PipelineConfig {
    String envName = 'Staging'
    String version = '1.0.0'
    boolean deploy = true
    boolean runTests = true

    // add these too
    String branch
    String repoUrl 
}

// class PipelineConfig {
//     String envName
//     String version
//     String branch
//     String repo
//     boolean enableTests = true   // default value example
//     boolean deploy = false
// }
