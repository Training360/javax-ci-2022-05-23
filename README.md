# Java CI tanfolyam

## Labor 1.

* Könyvtár létrehozás: `javax-ci-2022-05-23`
* Visual Studio Code indítása a könyvtárban
* Lokális Git repo létrehozása: `git init`
* Git felhasználónév és e-mail cím beállítása:

```shell
git config --global user.name "FIRST_NAME LAST_NAME"
git config --global user.email "MY_NAME@example.com"
```

* Felhasználói felületen `git add` és `git commit`

## Labor 2.

```shell
git remote add origin https://github.com/Training360/javax-ci-2022-05-23.git
git push origin master
```

Alternatíva:

```shell
mkdir javax-ci-2022-05-23-oktato
cd javax-ci-2022-05-23-oktato
git clone https://github.com/Training360/javax-ci-2022-05-23.git .
git pull
```

## Labor 3. - Alkalmazás buildelése

```
set JAVA_HOME=c:\Program Files\Java\jdk-17.0.3.1\
mvnw package
```

## Labor 4. - Alkalmazás futtatása

```
mvnw spring-boot:run
```

Majd elérhető lesz a `http://localhost:8080/api/employees` címen

http://localhost:8080/swagger-ui.html

## Labor 5. - Nexus

* Adminisztrátor módban parancssor:

```
net localgroup docker-users %USERDOMAIN%\%USERNAME% /add
```

* Kijelentkezés

Docker mirror, Settings / Docker engine:

```json

  "registry-mirrors": ["https://mirror.gcr.io"]

```

```shell
docker run --name nexus --detach --publish 8091:8081 --publish 8092:8082 sonatype/nexus3
```

Nexus elérhetősége: `http://localhost:8091/`

Admin jelszó:

```
docker exec -it nexus cat /nexus-data/admin.password
```

https://help.sonatype.com/repomanager3/nexus-repository-administration/formats/maven-repositories

`~/.m2/settings.xml`:

```xml
<settings>
  <mirrors>
    <mirror>
      <!--This sends everything else to /public -->
      <id>nexus</id>
      <mirrorOf>*</mirrorOf>
      <url>http://localhost:8091/repository/maven-public/</url>
    </mirror>
  </mirrors>
  <profiles>
    <profile>
      <id>nexus</id>
      <!--Enable snapshots for the built in central repo to direct -->
      <!--all requests to nexus via the mirror -->
      <repositories>
        <repository>
          <id>central</id>
          <url>http://central</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>true</enabled></snapshots>
        </repository>
      </repositories>
     <pluginRepositories>
        <pluginRepository>
          <id>central</id>
          <url>http://central</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>true</enabled></snapshots>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>
  <activeProfiles>
    <!--make the profile active all the time -->
    <activeProfile>nexus</activeProfile>
  </activeProfiles>
</settings>
```

## Labor 6. - Tesztlefedettség

* Módosult a `pom.xml` állomány: Jacoco

```
mvnw package
```

Tesztlefedettség report létrejön a `target/site/jacoco/index.html`

## Labor 7. - Integrációs tesztek

* Átmásolni az `src/test` könyvtár tartalmát
* Átmásolni a módosított `pom.xml` állományt
* Integrációs tesztek futtatása:

```
mvnw integration-test
```

## Labor 8. - E2E tesztek

* `Dockerfile` létrehozás
* Docker image létrehozása, parancsot kiadni a projekt főkönyvtárában

```
docker build -t employees-app .
```

Ha el akarom indítani az alkalmazást Dockeren belül:

```
docker run -p 8081:8080 -d --name employees-app-instance employees-app
```

* `docker-compose.yml` fájl létrehozása az `e2e` könyvtárban

Kiadni az `e2e` könyvtárban:

```
docker compose up -d
```

* Postmanben létrehozni egy kérést
* Majd létrehozni a tesztet

```javascript
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});
pm.test("Valid name", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.name).to.eql("Jack Doe");
});
```

* Exportálni: Collection, Environment

```shell
docker compose down
docker compose up --abort-on-container-exit
```

## Labor 9. - GitLab infrastruktúra elindítása

```
cd gitlab
docker compose up -d
docker exec -it gitlab-gitlab-1 grep "Password:" /etc/gitlab/initial_root_password
```

Felhasználónév: `admin`