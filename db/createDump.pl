use Config::Properties;

$properties = new Config::Properties();
open PROPS, "db.properties";
$properties->load(*PROPS);
$db = $properties->getProperty("db");
$user = $properties->getProperty("user");
$password = $properties->getProperty("password");
$dumpFile = $properties->getProperty("dumpFile");



`mysqldump  -u$user -p$password --no-create-info=FALSE --order-by-primary=FALSE --force=FALSE --no-data=FALSE --tz-utc=TRUE --flush-privileges=FALSE --compress=FALSE --replace=FALSE --host=127.0.0.1 --insert-ignore=FALSE --extended-insert=TRUE --user=root --quote-names=TRUE --hex-blob=FALSE --complete-insert=FALSE --add-locks=TRUE --port=3306 --disable-keys=TRUE --delayed-insert=FALSE --create-options=TRUE --delete-master-logs=FALSE --comments=TRUE --default-character-set=utf8 --max_allowed_packet=1G --flush-logs=FALSE --dump-date=TRUE --lock-tables=TRUE --allow-keywords=FALSE --events=FALSE  --add-drop-database $db > $dumpFile`;
