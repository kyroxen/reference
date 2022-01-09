# Create network
docker network create --driver bridge dev-network

# MYSQL

```docker run -d \
--name mysql_container \
--cap-add=sys_nice \
--network dev-network \
-v ~/elk/mysql/data-volume:/var/lib/mysql \
-p 3307:3306 \
-e MYSQL_ROOT_PASSWORD=my_root_pwd \
mysql:8.0.26
```

# MYSQL EXPORTER

```
docker run \
  --name mysql_exporter_container \
  -p 9104:9104 \
  --network dev-network  \
  -e DATA_SOURCE_NAME='exporter:1234567890@(mysql_container:3306)/' \
  prom/mysqld-exporter --collect.global_status --collect.info_schema.innodb_metrics \
 --collect.auto_increment.columns \
 --collect.info_schema.processlist \
 --collect.binlog_size \
 --collect.info_schema.tablestats \
 --collect.global_variables \
 --collect.info_schema.query_response_time \
 --collect.info_schema.userstats \
 --collect.info_schema.tables \
 --collect.perf_schema.tablelocks \
 --collect.perf_schema.file_events \
 --collect.perf_schema.eventswaits \
 --collect.perf_schema.indexiowaits \
 --collect.perf_schema.tableiowaits \
 --collect.slave_status
```

# NODE EXPORTER

```
docker run -d \
  --name node-exporter-container \
  --network dev-network \
  -p 9100:9100 \
  prom/node-exporter
```

# CADVISOR

```
docker run -d \
--name cadvisor-container \
--network dev-network \
-p 8081:8080 \
--volume /var/run:/var/run:rw \
--volume /sys:/sys:ro \
--volume /var/lib/docker/:/var/lib/docker:ro \
--volume /var/run/docker.sock:/var/run/docker.sock:rw \
google/cadvisor:latest
```

# INFUXDB

```
docker run -d \
--name influxdb-container \
--network dev-network \
-p 8086:8086 \
-v ~/elk/influxdb/volume:/var/lib/influxdb2 \
influxdb:latest
```

```
docker exec -it influxdb-container influx config create --config-name myConfig \
  --host-url http://localhost:8086 \
  --org org \
  --token TCxpfcmIHXme1SoDMO4IfQzg98r5e-ju0JlwC7WTTef0LDB9VszDYX-dR5CLlzMSTiyuTSyE9qZxerDaLGDCvw== \
  --active
```

# PROMETHEUS

```
docker run -d --name prometheus-container -p 9090:9090 --network dev-network -v ~/elk/prometheus/config:/etc/prometheus -v ~/elk/prometheus/data:/data prom/prometheus

```

# SWAGGER EDITOR

```docker run -d -p 8082:8080 swaggerapi/swagger-editor```

# JMETER TEST

```
./jmeter.sh -n -t ~/Documents/jmeter-tests/tncAPI.jmx -l ~/Documents/jmeter-tests/tncAPIResultOld.jtl -e -o ~/Documents/jmeter-tests/html-old
```

```
./jmeter.sh -n -t ~/Documents/jmeter-tests/tncAPINew.jmx -l ~/Documents/jmeter-tests/tncAPIResultNew.jtl -e -o ~/Documents/jmeter-tests/html-new
```

# DOCKER LATCH CONTAINERS ON THE BRIDGE
> docker network connect dev-network 

> docker network connect dev-network cdvisor-container

# MISC

container_cpu_usage_seconds_total{name="mysql_container"}

(
  (1 - rate(node_cpu_seconds_total{job="node", mode="idle", instance="$instance"}[$__interval]))
/ ignoring(cpu) group_left
  count without (cpu)( node_cpu_seconds_total{job="node", mode="idle", instance="$instance"})
)

