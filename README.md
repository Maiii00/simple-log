# Simple log

**simple-log** is an AOP-based logging library for Java.

## Quick Start
```shell
cd simple-log
mvn clean install
```

- Add dependency to your `pom.xml`
```xml
<dependency>
    <groupId>io.github.maiii00</groupId>
    <artifactId>simple-log</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- Use `@AutoLog` on a method
```java
@AutoLog
public void ...
```

## simple-log-example
- Example Output
```text
2026-03-06T17:56:04.672+08:00  INFO 58336 --- [   scheduling-1] i.g.maiii00.simple_log.aspect.LogAspect  : [Simple-Log] [SUCCESS] Method: runTask | Time: 446ms | Args: {}
```

```sql
 16 | {"id": "0.34624868467541947"} | 2026-03-06 13:33:09.399922 | processData |           |            107 | processData | t
 17 | {}                            | 2026-03-06 13:33:09.569642 | runTask     |           |            277 | runTask     | t
 18 | {"id": "0.06759300435068161"} | 2026-03-06 13:34:09.392673 | processData |           |            108 | processData | t
 19 | {}                            | 2026-03-06 13:34:09.393198 | runTask     |           |            109 | runTask     | t
 20 | {"id": "0.46110288936084276"} | 2026-03-06 17:56:04.340976 | processData |           |            112 | processData | t
```