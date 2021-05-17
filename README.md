# News into the Disc

News into the Disc is a Android Application.

##Domain Model

```
@startuml

    package externals* #ffcccc{

        package org.threeten.bp{
                
                class zonedDateTime{
                ...
            }

        }    

    }

package cl.ucn.disc.dsm.rodobollmann{


    package model #ccffcc{
    
        class News <<entity>> {
            - id: Long
            - title: String
            - source : String
            - author : String
            - url : String
            - urlImage : String
            - description: String
            - content : String
            + News (..)
            + getId(): String
            + getTitle(): Stringg
            + getAuthor(): String
            + getUrl(): String
            + getUrlImage(): String
            + getDescription(): String
            + getContent(): String
        }

        News *--> "1" ZonedDateTime : - publishedAt
    }

    package services #ccccff{

    interface Contracts <<interface>> { 
        + retrieveNews(size: Intenger) : List <News>
        + save(news: News) : void
      }
      Contracts ..> News : <<use>>
    }

}

@enduml
```

## License
[MIT](https://choosealicense.com/licenses/mit/)