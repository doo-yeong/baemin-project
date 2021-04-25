# baemin-project
안드로이드 프로젝트 - 배달의 민족 클론 코딩 





## Baemin-Android

### 1. Environment

- Android Studio 4.1.2
- Kotlin 1.4.21

### 2. SDK Version

- minSdkVersion : 21
- targerSdkVersion : 30



## Baemin-Server

### 1. Environment

- H2
- Java 11
- gradle 6.7.1

### 2. Build (without test)

```
baemin-project\server\baeminclone> gradlew clean build -x test
```

### 3. Run jar

```
baemin-project\server\baeminclone\build\libs> java -jar <file name>
```