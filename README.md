<h1 align='center'>OOP Movie App</h1>
<hr>
<h2>Technologies stack:</h2>
<ol>
    <li>
        <h3>Back-End:</h3>
        <ul>
            <li>Java</li>
            <li>Spring Boot</li>
            <li>Thymeleaf</li>
        </ul>
    </li>
    <li>
        <h3>DataBase:</h3>
        <ul>
            <li>sqlite</li>
        </ul>
    </li>
    <li>
        <h3>Front-End:</h3>
        <ul>
            <li>HTML</li>
            <li>CSS</li>
            <li>Java Script</li>
        </ul>
    </li>
</ol>
<hr>
<h2>Home page</h2>
<img src="./img/home.png" alt="homePageImg" />
<hr>
<h2>Home page authentication</h2>
<img src="./img/homeAuth.png" alt="homePageImg" />
<p>User is shown in right top corner</p>
<hr>
<h2>Home page admin</h2>
<img src="./img/homeAdmin.png" alt="homePageImg" />
<p>Admin can delete movies or change movie background</p>
<img src="./img/editImageAdmin.png" alt="homePageImg" />
<hr>
<h2>Sign In</h2>
<img src="./img/signIn.png" alt="signIn" />
<hr>
<h2>Login</h2>
<img src="./img/login.png" alt="login" />
<hr>
<h2>Admin Settings Page</h2>
<img src="./img/adminSettings.png" alt="adminSettingsPage" />
<p>Admin can delete users</p>
<hr>
<h2>Movie Details Page</h2>
<img src="./img/movieDetails.png" alt="movieDetailsPage" />
<p>All comments are shown in movie detail page</p>
<hr>
<h2>Comments Not Auth</h2>
<img src="./img/commentsNotAuth.png" alt="commentsNotAuth" />
<p>Only authorized users can add comments</p>
<hr>
<h2>Admin Add Movies</h2>
<img src="./img/adminAddMovies.png" alt="adminAddMovies" />
<p>Admin can add new movies</p>
<hr>
<h2>Ďalšie kritériá:</h2>
<ul>
    <li>
        <p>použitie návrhových vzorov okrem návrhového vzoru Singleton – každý implementovaný návrhový vzor sa počíta ako splnenie jedného ďalšieho kritériá, ale implementácia všetkých návrhových vzorov sa posudzuje maximálne na úrovni splnenia troch ďalších kritérií</p>
        <hr>        
        <p>Splnene (Observer pre admina na generovanie reviews, Factory pre 'Entities', Proxy pre movies)</p>
    </li>
    <li>
        <p>ošetrenie mimoriadnych stavov prostredníctvom vlastných výnimiek – stačí jedna vlastná výnimka, ale musí byť skutočne vyhadzovaná a ošetrovaná</p>
        <hr>        
        <p>Splnene (IOException, MovieNotFoundException, ReviewNotFoundException, UserNotFoundException)</p>
    </li>
    <li>
        <p>poskytnutie grafického používateľského rozhrania oddelene od aplikačnej logiky a s aspoň časťou spracovateľov udalostí (handlers) vytvorenou manuálne – počíta sa ako splnenie dvoch ďalších kritérií</p>
        <hr>        
        <p>Splnene (Java v subore java; HTML, CSS, JS v subore resources)</p>
    </li>
    <li>
        <p>explicitné použitie viacniťovosti (multithreading) – spustenie vlastnej nite priamo alebo prostredníctvom API vyššej úrovne (trieda Task a pod.)</p>
        <hr>
        <p>Splnene (mam multistreaming a api taktiež aj spolu s Thymeleaf)</p>
    </li>
    <li>
        <p>použitie generickosti vo vlastných triedach – implementácia a použitie vlastnej generickej triedy (ako v príklade spájaného zoznamu poskytnutého k prednáške 5)</p>
        <hr>
        <p>Splnene (1: Person ktory extendujem v User a Admin; 2: Blank ktory extendujem v Movie a Comment, 3: JPARepository, ktory použivam v repozitaroch)</p>
    </li>
    <li>
        <p>explicitné použitie RTTI – napr. na zistenie typu objektu alebo vytvorenie objektu príslušného typu (ako v hre s obrami a rytiermi pri zisťovaní počtu bytostí)</p>
        <hr>
        <p>Splnene (mam v kode typeof)</p>
    </li>
    <li>
        <p>použitie vhniezdených tried a rozhraní – počíta sa iba použitie v aplikačnej logike, nie v GUI, pričom rozhrania musia byť vlastné (jedna možnosť je v príklade vnútorných tried k prednáške 4)</p>
        <hr>
        <p>Splnene (ano)</p>
    </li>
    <li>
        <p>použitie lambda výrazov alebo referencií na metódy (method references) – počíta sa iba použitie v aplikačnej logike, nie v GUI (jedna možnosť je v príklade referencií na metódy a lambda výrazov k prednáške 4)</p>
        <hr>
        <p>Splnene ("::")</p>
    </li>
    <li>
        <p>použitie implicitnej implementácie metód v rozhraniach (default method implementation)</p>
        <hr>
        <p>Splnene (ano)</p>
    </li>
    <li>
        <p>použitie aspektovo-orientovaného programovania (AspectJ)</p>
        <hr>
        <p>Splnene (pre logovanie LoggingAspect)</p>
    </li>
    <li>
        <p>použitie serializácie</p>
        <hr>
        <p>Splnene (Review implements Serializable)</p>
    </li>
</ul>
