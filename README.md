# UserSorting-Test-Solution

##  Run UserSorting-Test-Solution application 

```
git clone git@github.com:mahendra-s/UserSorting-Test-Solution.git
cd UserSorting-Test-Solution
sbt clean run 
```
* Select **[1] com.example.BatchMain** for batch 
* Select **[2] com.example.QuickstartServer** for restful web service

### Batch mode output
Output after running application will be written in project home directory. Already executed output can be seen in
[Sample Output File](outfile.txt)

### Output of restful can be downloaded by hitting url 
[http://localhost:8080/api/1.0/outfile.txt](http://localhost:8080/api/1.0/outfile.txt)


Note: Application can be configured via [application.conf](src/main/resources/application.conf).
Configurations:  
```
output.separator {
  column = ", "
  line = "\n"
}

geo.point {
  latitude = 17.387140
  longitude = 78.491684
}

input.url = "https://jsonplaceholder.typicode.com/users"

outfile.name = "outfile.txt"
```

