# simbirsoftLayoutTask

## Верстка

---

### Теоретическая часть

В случае если по ссылке встречается пошаговый гайд - рекомендуется его выполнить в отдельном проекте.

**1. Начало разработки под Android**

+ [Начало разаработки](https://developer.android.com/training/index.html) **(\*\*\*\*)**

**2. Верстка**

+ [Уроки верстки из курсов](http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html) **(\*\*)**

+ [Создание макетов в XML и View groups](https://developer.android.com/guide/topics/ui/declaring-layout.html) **(\*\*\*)**

**3. Типы layout'ов**

+ [Frame Layout](http://developer.alexanderklimov.ru/android/layout/framelayout.php) **(\*\*\*\*)**

+ [Linear Layout](https://developer.android.com/guide/topics/ui/layout/linear.html) **(\*\*\*\*)**

+ [Relative Layout](https://developer.android.com/guide/topics/ui/layout/relative.html) **(\*\*\*\*)**

**4. Splash Screen**

+ [Как правильно реализовать](https://habr.com/ru/post/345380/) **(\*\*\*\*)**

**4. BottomNavigationView**

+ [Обзор](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html) **(\*\*\*\*)**

**5. App Bar**

+ [Обзор](https://developer.android.com/training/appbar) **(\*\*\*\*)**

**6. Constraint Layout**

+ [Документация](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) **(\*\*\*\*)**

+ [Работа с различными свойствами](https://habrahabr.ru/company/touchinstinct/blog/326814/) **(\*\*\*\*)**

**7. Ресурсы**

+ [Обзор](https://developer.android.com/guide/topics/resources/providing-resources) **(\*\*\*\*)**

+ [Локализация](https://developer.android.com/guide/topics/resources/localization) **(\*\*)**

+ [Типы ресурсов](https://developer.android.com/guide/topics/resources/available-resources) **(\*\*)**

+ [Шрифты в XML](https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml.html) **(\*\*)**

+ [Загружаемые шрифты](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html) **(\*\*)**

+ [Поддержка разных экранов](https://developer.android.com/guide/practices/screens_support.html) **(\*\*)**

+ [Zeplin](https://habrahabr.ru/company/uteam/blog/315542/) **(\*\*\*)**

### Практическое задание

Для проверки все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Код должен быть читабельным и написан согласно code-style.

1. Создать в YourTrack issue с названием "Layouts", назначить его на себя и при начале работы выставить State: "In Progress"

2. Создать новый github проект и подготовить проект к работе

- создать в нем основную ветку master с новым Android проектом

- создать ветку develop от master

- создать ветку layouts от develop, работу следует вести в ветке layouts

- добавить README.md с содержимым этого файла

- добавить ссылку на github в комментарии issue из п.1

3. Сделать так, чтобы на домашнем экране Android отображалась иконка и название приложения "Хочу помочь". Ресурсы иконок [тут](https://zpl.io/2jkoMOp).

4. Реализовать Splash Screen согласно [макету](https://zpl.io/2jlk3Mm).

5. Реализовать экран "Профиль" согласно [макету](https://zpl.io/b6lQpZq).

- Экран "Профиль" необходимо отображать после Splash Screen. По нажатию стрелки назад, приложение закрывается.

- Необходимо реализовать нижний элемент навигации с помощью стандартного `BottomNavigationView`. Пункт "Помочь" визуально не должен отличаться от остальных четырех. Размеры иконок оставить стандартные для `BottomNavigationView` - 24dp.

- В нижнем меню навигации по-умолчанию должен быть выбран пункт "Профиль".

- Верстка должна быть реализована в xml.

- Верстка должна быть выполнена с учетом "pixel perfect" - когда все элементы дизайна расположены и имеют размеры абсолютно идентичные макету для экрана с теми же размерами, что и макет, и адекватно масштабироваться для других размеров и разрешений.

- Все переиспользуемые размеры в xml должны быть вынесены в dimes, цвета в colors, а строки в strings.

- Никаких "магических чисел", все должно иметь понятные названия.

6. По завершению работы (когда ментор подтвердил что все готово) сделать merge ветки layouts в develop и выставить у issue в YouTrack State: Fixed

