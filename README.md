# WordsFactory

Android-приложение для зубрежки английских слов.

Поиск слов происходит:
- на сервере, если есть подключение к интернету
- в БД, если подключение к интернету отсутствует

Приложение позволяет проводить тестирование с словами из словаря.


### API

https://api.dictionaryapi.dev/api/v2/entries/en/{word}

Пример:

https://api.dictionaryapi.dev/api/v2/entries/en/cooking 

### Стек технологий:

MVVM, Clean Architecture, Hilt, ViewBinding, RecyclerView, Coroutine, Retrofit2, Room, ViewPager2, ValueAnimator

<details><summary> UI </summary>

![словарь75](https://user-images.githubusercontent.com/94065894/187353291-f3807942-0fdb-478c-a08b-a09b3d7fa34f.png)

</details>
