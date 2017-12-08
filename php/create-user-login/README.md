These small examples are NOT examples on good design or good PHP practice.

They serve merely as examples on how one could test the navigation and logic of a small web server login, using a database.

The password should of course be encrypted before storing in the database (and hashed by the php script), and the SQL should be replaced with place holders in the prepared statement to protect against SQL injection.

The scripts show how one could insert users to the database, and how one could test the login.php script from the command line (without the hassle of using the HTML form manually).

Please see these small examples for what they are - small examples showing a principle and not production quality code.

We, the authors, don't even claim to know the slightest bit about php!

We only wanted to show some small scripts to use in conjunction with php and a database setup.

The scripts:

```bash
$ ./insert_user.sh
Usage: ./insert_user.sh <username> <email> <password> <name>

$ ./test_login.sh
USAGE: ./test_login.sh <user> <password>
```


