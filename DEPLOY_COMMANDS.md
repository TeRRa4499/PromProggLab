# Maven + Tomcat команды

## Проверка окружения

```powershell
java -version
mvn -version
```

## Сборка проекта

```powershell
mvn clean package
```

## Развертывание в Tomcat через Maven (Cargo)

```powershell
mvn clean package cargo:deploy
```

## Обновление уже развернутого приложения

```powershell
mvn cargo:redeploy
```

## Удаление приложения из Tomcat

```powershell
mvn cargo:undeploy
```

## Развертывание с передачей логина/пароля из командной строки

```powershell
mvn clean package cargo:deploy -Dtomcat.username=admin -Dtomcat.password=admin
```

## Локальный запуск Tomcat (если нужно вручную)

```powershell
set CATALINA_HOME=c:\tomcat\apache-tomcat-11.0.21
%CATALINA_HOME%\bin\startup.bat
```

## Остановка Tomcat

```powershell
%CATALINA_HOME%\bin\shutdown.bat
```

## Примечания

- В `pom.xml` должен быть настроен `cargo-maven2-plugin`.
- Путь приложения задается через `tomcat.path` (по умолчанию `/student-group-manager`).
- Для Tomcat user должен иметь роль `manager-script`.
