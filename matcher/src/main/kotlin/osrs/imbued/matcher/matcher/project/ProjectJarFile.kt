package osrs.imbued.matcher.matcher.project

class ProjectJarFile() {

    constructor(fileName: String, fileChecksum: String, type: ProjectJarType) : this() {
        this.fileName = fileName
        this.fileChecksum = fileChecksum
        this.type = type
    }

    lateinit var fileName: String
    lateinit var fileChecksum: String
    lateinit var type: ProjectJarType
}