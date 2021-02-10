DownloadApp

API to connect and download JSON files from: "https://jsonplaceholder.typicode.com"

To save posts to folder with given name:

    http://localhost:8080/posts?folder=folderName (posts are save with postId as name)

To save comments from first n posts and save to folder with given name:

    http://localhost:8080/comments?howMany=10&folder=folderName (comments are save with domain as name)
