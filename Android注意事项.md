# Android注意事项

自己手动提交Android Studio的项目时，注意不要提交：
* /build
* /.idea
* /.gradle
* *.iml
* local.properties

注：通过Android Stduio新建的项目会生成.gitignore文件，
会帮忙忽略文件，其内容大致如下，我们可以自己定制：
*.iml
.gradle
/local.properties
/.idea/workspace.xml
/.idea/libraries
.DS_Store
/build
/captures
.externalNativeBuild
