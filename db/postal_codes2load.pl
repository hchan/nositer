use Config::Properties;

$properties = new Config::Properties();
open PROPS, "db.properties";
$properties->load(*PROPS);
$db = $properties->getProperty("db");
$user = $properties->getProperty("user");
$password = $properties->getProperty("password");

open FH, "postal_codes.csv";
$loadfile = "postalcode.load";
open WH, ">$loadfile"; # In mysqlimport, the name of the datafile should match the name of the table. The extension of the datafile can be anything.


$line = <FH>; # skip the first line
$count = 0;
while ($line = <FH>) {
    $count++;
    chomp $line;
    #($id, $postalcode, $city, $province, $provincecode, $citytype, $latitude, $longitude) = split("\\|", $line);
    #print qq|INSERT INTO POSTALCODE VALUES($count, "$postalcode", "$city", "$province", "$pronvincecode", "$citytype", "$latitude", "$longitude");\n|;
    @fields = split("\\|", $line);
    for ($i = 0; $i <= $#fields; $i++) {
	print WH $fields[$i];
	#if ($i != $#fields) {
	    print WH "\t";
	#}
    }
    print WH "\n";
}


close WH;
close FH;
print "Please run:\nmysqlimport -u$user -p$password --local $db $loadfile\n";
