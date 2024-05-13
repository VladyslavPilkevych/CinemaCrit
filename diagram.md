classDiagram
direction BT
class Admin {
  + update(Movie) void
}
class AdminController {
  - MovieFactory movieFactory
  - MovieService movieService
  - AuthenticationService authenticationService
  - MovieProxy movieProxy
  - ReviewService reviewService
  - MovieRepository movieRepository
  + showMovieList(Model) String
  + addMovie(Movie) String
  + removeMovie(String) String
  + uploadMovieImage(Long, String) ResponseEntity~String~
  + removeUser(String) String
}
class AuthenticationController {
  - UserRepository authenticationRepository
  - PersonFactory personFactory
  - AuthenticationService authenticationService
  + login(Map~String, Object~) ResponseEntity~Map~String, Object~~
  + showLoginForm(Model) String
  + logout() String
}
class AuthenticationService {
  - UserRepository userRepository
  + logout() void
  + isUserExist(String) boolean
  + getAllUsers() List~User~
  + isEmailExist(String) boolean
  + removeUserById(Long) void
  + authenticate(String, String) boolean
}
class BaseEntityFactory {
  + createAdmin() Admin
  + createReview() Review
  + createUser() User
  + createMovie() Movie
}
class Blank {
  - String title
  - String description
  - Long id
  - int year
  + setId(Long) void
  + setYear(int) void
  + getId() Long
  + setTitle(String) void
  + getDescription() String
  + setDescription(String) void
  + getTitle() String
  + getYear() int
}
class Comment {
  - LocalDateTime createdDate
  + setCreatedDate(LocalDateTime) void
  + getCreatedDate() LocalDateTime
}
class DataSourceConfiguration {
  ~ Environment env
  + dataSource() DataSource
}
class DateUtils {
  + getAgoString(LocalDateTime) String
}
class EntityFactory {
<<Interface>>
  + createAdmin() Admin
  + createMovie() Movie
  + createUser() User
  + createReview() Review
}
class IOException {
  ~ long serialVersionUID
}
class LoggingAspect {
  - Logger logger
  - movieControllerMethods() void
  + logMovieControllerMethods() void
}
class MainController {
  + index() String
}
class Movie {
  - List~Review~ reviews
  - String image
  - List~MovieObserver~ observers
  + removeObserver(MovieObserver) void
  + getAverageRating() double
  + getImage() String
  + setImage(String) void
  + addObserver(MovieObserver) void
  + notifyObservers() void
}
class MovieAppApplication {
  + main(String[]) void
}
class MovieController {
  - ReviewService reviewService
  - ReviewRepository reviewRepository
  - MovieService movieService
  - ReviewFactory reviewFactory
  + showMovieList(String, Model) String
  + showMovieDetails(Long, Model) String
  + addReview(String, String, Review) String
}
class MovieFactory {
  + createMovie() Movie
}
class MovieNotFoundException
class MovieObserver {
<<Interface>>
  + update(Movie) void
}
class MovieProxy {
<<Interface>>
  + deleteMovie(Long) void
  + addMovie(Movie) void
  + updateMovieImage(Long, String) void
}
class MovieProxyImpl {
  - MovieService movieService
  + updateMovieImage(Long, String) void
  + addMovie(Movie) void
  + deleteMovie(Long) void
}
class MovieRepository {
<<Interface>>
  + findAllByOrderByAverageRatingDesc() List~Movie~
  + findMoviesByPopularity() List~Movie~
  + findAllByOrderByYearDesc() List~Movie~
}
class MovieService {
  - MovieRepository movieRepository
  + getMoviesFilteredByRate() List~Movie~
  + updateMovie(Movie) void
  + getMoviesFilteredByDate() List~Movie~
  + getMoviesFilteredByPopularity() List~Movie~
  + getAllMovies() List~Movie~
  + getMovieById(Long) Movie
  + addMovie(Movie) void
  + deleteMovie(Long) void
}
class PasswordEncryptor {
  + encryptPassword(String) String?
}
class Person {
  - String username
  - Long id
  - String password
  + getUsername() String
  + setId(Long) void
  + setUsername(String) void
  + getId() Long
  + getPassword() String
  + setPassword(String) void
}
class PersonFactory {
  + createUser() User
  + createAdmin() Admin
}
class Review {
  - String comment
  - String username
  - int rating
  - Long movieId
  - LocalDateTime createdDate
  - Long id
  + getCreatedDate() LocalDateTime
  + getUsername() String
  + getMovieId() Long
  + serializeReview(Review, String) void
  + setUsername(String) void
  + getId() Long
  + setComment(String) void
  + getRating() int
  + setMovieId(Long) void
  + setCreatedDate(LocalDateTime) void
  + setId(Long) void
  + getComment() String
  + setRating(int) void
}
class ReviewController {
  - ReviewService reviewService
  - ReviewFactory reviewFactory
  + addReview(Long, Review) String
  + showReviews(Long, Model) String
}
class ReviewFactory {
  + createReview() Review
}
class ReviewNotFoundException
class ReviewRepository {
<<Interface>>
  + findAllByMovieId(Long) List~Review~
}
class ReviewService {
  - ReviewRepository reviewRepository
  + getReviewsByMovieId(Long) List~Review~
  + getAllReviews() List~Review~
  + addReview(Review) Review
}
class User {
  - String email
  + getEmail() String
  + setEmail(String) void
}
class UserNotFoundException
class UserRepository {
<<Interface>>
  + findByEmail(String) Optional~User~
  + findByUsername(String) Optional~User~
}

Admin  ..>  MovieObserver 
Admin  -->  Person 
BaseEntityFactory  ..>  EntityFactory 
Comment  -->  Blank 
Movie  -->  Blank 
MovieFactory  -->  BaseEntityFactory 
MovieProxyImpl  ..>  MovieProxy 
PersonFactory  -->  BaseEntityFactory 
ReviewFactory  -->  BaseEntityFactory 
User  -->  Person 
