use Config::Properties;

$properties = new Config::Properties();
open PROPS, "db.properties";
$properties->load(*PROPS);
$db = $properties->getProperty("db");
$user = $properties->getProperty("user");
$password = $properties->getProperty("password");
$dumpFile = $properties->getProperty("dumpFile");

`mysql -u$user -p$password $db < $dumpFile`;
