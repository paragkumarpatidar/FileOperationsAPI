# FileOperationsAPI
Basic File Operations API which perform operations like download, delete and copy files

Few Assumoptions being made while developing this API
- File is present in the "C:/Files/" server directory  which can be changed by the base location property in application.yml file
- Copy the file in same directory with 'copy' prefix added to original fileName.

Download API
- When provided with required file name, it starts downloading file if it exists on server

Delete API
- When provided with required file name, it deletes the file if it exists on server

Create API
 - When provided with required file name, it creates the copy of the file on same location

For API Information refer Swagger Documentation attaced in repository
