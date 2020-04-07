package osrs.imbued.matcher.matcher.project

data class ProjectJarFile(
    val fileName: String,
    val fileChecksum: String,
    val type: ProjectJarType
)