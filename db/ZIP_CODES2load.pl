use Text::CSV;
use Config::Properties;
$properties = new Config::Properties();
open PROPS, "db.properties";
$properties->load(*PROPS);
$db = $properties->getProperty("db");
$user = $properties->getProperty("user");
$password = $properties->getProperty("password");

$csv = Text::CSV->new();
open FH, "ZIP_CODES.txt";
$loadfile = "zipcode.load";
open WH, ">$loadfile";  # In mysqlimport, the name of the datafile should match the name of the table. The extension of the datafile can be anything.

$count = 0;
while ($line = <FH>) {
    $count++;
    $csv->parse($line);
    @fields = $csv->fields();
    print WH "$count\t";
    for ($i = 0; $i <= $#fields; $i++) {

	print WH $fields[$i];
	print WH "\t";
	if ($i == 0) {
	    print WH "\t"; # description
	}

    }
    print WH "\n";
}



close WH;
close FH;
print "Please run:\nmysqlimport -u$user -p$password --local $db $loadfile\n";
