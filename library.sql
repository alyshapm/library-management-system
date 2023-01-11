-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 11, 2023 at 05:06 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminId` int(10) NOT NULL,
  `adminFname` varchar(20) NOT NULL,
  `adminLname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNo` varchar(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `adminRole` varchar(4) NOT NULL DEFAULT 'part'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`, `adminFname`, `adminLname`, `email`, `phoneNo`, `username`, `password`, `address`, `adminRole`) VALUES
(1, 'Valentine', 'Benson', 'valentinebenson@mail.com', '87654528530', 'valentinebenson', 'bensonV1', 'Jl Prof Dr Satrio Mal Ambasador, Jakarta', 'part'),
(2, 'Carmen', 'Daniels', 'carmenbenson@mail.com', '8478512763', 'carmenbenson', 'danielsC2', 'Jl Cipulir IV/29', 'full'),
(3, 'Samuel', 'Patton', 'samuelpatton@mail.com', '86729943737', 'samuelpatton', 'pattonS3', 'Jl AM Sangaji 28', 'full'),
(4, 'Jessica ', 'Moreno', 'jessicamoreno@mail.com', '83533908979', 'jessicamoreno', 'morenoJ4', 'Jl Pemuda Kav 3-4 Arion Plaza', 'part'),
(5, 'Noah', 'Erickson', 'noaherickson@mail.com', '83223807515', 'noaherickson', 'ericksonN5', 'Jl Bendungan Melayu Gg Mandiri 40 RT 04/02 Rawa Badak', 'part'),
(6, 'Alfie', 'Grey', 'alfiegrey@mail.com', '84800627236', 'alfiegrey', 'greyA6', 'Jl Pluit Raya 32 AC', 'full'),
(7, 'Naomi ', 'Andrews', 'naomiandrews@mail.com', '85763017025', 'naomiandrews', 'andrewsN7', 'Jl Alaydrus 70 B', 'full'),
(8, 'Kennedy', 'Walsh', 'kennedywalsh@mail.com', '80346380884', 'kennedywalsh', 'walshK8', 'Jl Kabel Pendek RT 013/02', 'full'),
(9, 'Devorah', 'Roloff', 'devorahroloff@mail.com', '81918265445', 'devorahroloff', 'roloffD9', 'Jl Tmn Meruya Ilir Bl G-2/19 RT 012/07 Meruya Utr', 'part'),
(10, 'Jesse ', 'James', 'jessejames@mail.com', '82118241100', 'jessejames', 'jamesJ10', 'Jl Letjen Haryono MT Kav 10 Tebet Brt', 'part');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookId` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author_name` varchar(50) NOT NULL,
  `publishId` int(10) NOT NULL,
  `serialNo` varchar(13) NOT NULL,
  `quantity` int(11) NOT NULL,
  `regisDate` date NOT NULL,
  `adminId` int(11) NOT NULL,
  `availability` int(20) NOT NULL,
  `shelfNo` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `genre` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookId`, `title`, `author_name`, `publishId`, `serialNo`, `quantity`, `regisDate`, `adminId`, `availability`, `shelfNo`, `floor`, `genre`) VALUES
(1, 'A Brief History of time', 'Stephen Hawking', 31, '9780553380163', 17, '2007-08-31', 1, 14, 978, 2, 'Popular Science'),
(2, 'A Vindication of the Rights of Woman', 'Mary Wollstonecraft', 19, '9780486290362', 20, '2011-08-20', 10, 13, 978, 3, 'Non-Fiction'),
(3, 'Critique of Pure Reason', 'Immanuel Kant', 33, '9781463794767', 7, '2006-11-04', 2, 3, 979, 2, 'Treatise, Non-Fiction'),
(4, 'Silent Spring', 'Rachel Carson', 11, '9780618249060', 26, '2003-04-14', 9, 20, 978, 1, 'Non-Fiction'),
(5, 'To Paradise', 'Hanya Yanagihara', 13, '9781529077483', 27, '2010-09-12', 3, 17, 979, 4, 'Historical Fiction, Dystopian Fiction'),
(6, 'The Complete Works', 'William Shakespeare', 43, '9780321886514', 13, '2018-01-12', 8, 13, 979, 4, 'Drama'),
(7, 'The Prince', 'Niccolo Machiavelli', 20, '9781514649312', 14, '2009-11-09', 4, 10, 978, 2, 'Non-Fiction'),
(8, 'The Republic', 'Plato', 4, '9781420931693', 9, '2018-01-10', 7, 4, 978, 2, 'Reference work, Utopian fiction'),
(9, 'The Wealth of Nations', 'Adam Smith', 39, '9781604598919', 19, '2005-06-23', 5, 13, 978, 2, 'Non-Fiction'),
(10, 'The Great Gatsby', 'F. Scott Fitzgerald', 18, '9780743273565', 22, '2011-11-17', 6, 22, 978, 4, 'Novel, Fiction, Tragedy'),
(11, 'To Kill a Mockingbird', 'Harper Lee', 10, '9780446310789', 21, '2014-04-13', 1, 12, 979, 3, 'Novel, Bildungsroman, Southern Gothic, Domestic Fiction, Thriller, Legal Story'),
(12, 'Romeo and Juliet', 'William Shakespeare', 8, '9780671722852', 16, '2014-03-20', 10, 10, 978, 4, 'Tragedy'),
(13, 'The Odyssey', 'Homer', 6, '9780140268867', 6, '2011-03-18', 2, 2, 978, 1, 'Epic poetry, Epic'),
(14, 'Hamlet', 'William Shakespeare', 39, '9780743477123', 28, '2013-03-17', 9, 20, 978, 1, 'Tragedy, Drama'),
(15, 'Macbeth', 'William Shakespeare', 6, '9780743477109', 15, '2019-12-02', 3, 7, 978, 1, 'Tragedy'),
(16, 'Of Mice and Men', 'John Steinbeck', 26, '9780140177398', 3, '2009-12-26', 8, 1, 978, 3, 'Fiction, Novella, Tragedy'),
(17, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 30, '9780062316097', 8, '2022-08-19', 4, 3, 979, 2, 'Non-Fiction'),
(18, 'Pride and Prejudice ', 'Jane Austen', 41, '9780486284736', 5, '2019-01-03', 7, 2, 979, 4, 'Fiction, Romance novel, Cookbook, Historical romance, Regency romance'),
(19, 'Mythologies', 'Roland Barthes', 32, '9780374521509', 12, '2017-09-06', 5, 9, 978, 3, 'Anthology'),
(20, 'It Ends with Us', 'Colleen Hoover', 8, '9781501110368', 18, '2009-05-06', 6, 14, 978, 4, 'Romance novel, Fiction, Contemporary romance'),
(21, 'Nineteen Eighty-Four', 'George Orwell', 10, '9781787302549', 17, '2015-05-09', 1, 16, 979, 2, 'Science fiction, Social science fiction, Dystopian Fiction, Political fiction'),
(22, 'Fahrenheit 451', 'Ray Bradbury', 20, '9781451673319', 7, '2010-03-19', 10, 2, 979, 2, 'Novel, Science fiction, Dystopian Fiction, Political fiction'),
(23, 'Lord of the Flies', 'William Golding', 22, '9781403991966', 23, '2006-02-09', 2, 20, 979, 3, 'Novel, Allegory, Young adult fiction'),
(24, 'The Count of Monte Cristo', 'Alexandre Dumas', 39, '9780553213508', 19, '2017-10-23', 9, 16, 978, 1, 'Novel, Serialised Work, Romance novel, Historical Fiction, Adventure fiction'),
(25, 'All Quiet on the Western Front', 'Erich Maria Remarque', 26, '9780099532811', 27, '2005-08-22', 3, 21, 978, 3, 'Novel, Fiction, War story, Roman à clef'),
(26, 'Moby-Dick or, the Whale', 'Herman Melville', 7, '9780140390841', 4, '2003-12-03', 8, 2, 979, 4, 'Epic, Adventure fiction, Nautical fiction'),
(27, 'Pandoras Jar: Women in the Greek Myths', 'Natalie Haynes', 18, '9781509873142', 9, '2018-10-05', 4, 3, 979, 3, 'Fiction, Fairy tale'),
(28, 'Brave New World', 'Aldous Huxley', 34, '9780062696120', 2, '2011-11-20', 7, 1, 979, 1, 'Novel, Science fiction, Dystopian Fiction'),
(29, 'The Grapes of Wrath', 'John Steinbeck', 13, '9780606001748', 11, '2005-09-24', 5, 5, 978, 2, 'Novel, Historical Fiction'),
(30, 'Blink: The Power of Thinking Without Thinking', 'Malcolm Gladwell', 3, '9780316010665', 26, '2022-08-06', 6, 16, 979, 2, 'Self-help book'),
(31, 'Caste: The Origins of Our Discontent', 'Isabel Wilkerson', 18, '9780593230251', 8, '2010-04-14', 1, 4, 978, 3, 'Non-Fiction'),
(32, 'Thinking, Fast and Slow', 'Daniel Kahneman', 17, '9780374533557', 16, '2006-01-28', 10, 10, 978, 4, 'Non-Fiction'),
(33, 'Just Mercy: A Story of Justice and Redemption', 'Bryan Stevenson', 36, '9789123979615', 29, '2017-10-31', 2, 23, 978, 1, 'Biography, Autobiography'),
(34, 'Catcher in the Rye', 'J. D. Salinger', 23, '9787543321724', 15, '2003-08-18', 9, 12, 978, 2, 'Novel, Bildungsroman, Young adult fiction, Coming-of-age story, First-person narrative, Literary realism'),
(35, 'The Rules of Attraction', 'Bret Easton Ellis', 37, '9780679781486', 6, '2006-06-16', 3, 3, 979, 4, 'Novel, Satire, Literary fiction, Dark comedy'),
(36, 'The Bell Jar', 'Sylvia Plath', 29, '9780061148514', 13, '2004-03-10', 8, 2, 978, 2, 'Novel, Autobiography, Autobiographical novel, Psychological Fiction, Roman à clef, Fictional Autobiography'),
(37, 'Cat’s Cradle', 'Kurt Vonnegut', 17, '9780385333481', 22, '2013-07-15', 4, 17, 979, 3, 'Novel, Science fiction, Satire, Humor'),
(38, 'Life of Pi', 'Yann Martel', 44, '9781786894243', 18, '2015-12-20', 7, 9, 979, 4, 'Novel, Adventure fiction, Psychological Fiction'),
(39, 'The Rachel Papers', 'Martin Amis', 23, '9780679734581', 12, '2006-07-28', 5, 7, 979, 1, 'Fiction'),
(40, 'On Beauty', 'Zadie Smith', 38, '9780143037743', 5, '2022-07-04', 6, 2, 978, 2, 'Novel, Romance novel, Domestic Fiction'),
(41, 'List That Changed My Life', 'Olivia Beirne', 7, '9781472259561', 7, '2017-02-08', 1, 5, 978, 3, 'Fiction, Romance novel, Contemporary romance'),
(42, 'The People We Keep', 'Alison Larkin', 12, '9781982171308', 9, '2022-02-24', 10, 6, 979, 4, 'Coming-of-age story, Historical Fiction, Bildungsroman, Domestic Fiction, Road Fiction'),
(43, 'Atlas of the Heart: Mapping Meaningful Connection and the Language of Human Experience', 'Brené Brown', 20, '9780593207246', 14, '2011-11-29', 2, 10, 979, 4, 'Self-help book'),
(44, 'Fangirl', 'Rainbow Rowell', 5, '9781250030955', 21, '2016-01-08', 9, 11, 978, 3, 'Novel, Young adult fiction'),
(45, 'Unbecoming', 'Jenny Downham', 10, '9780545907170', 23, '2003-03-10', 3, 18, 978, 1, 'Fiction'),
(46, 'Never Let Me Go', 'Kazuo Ishiguro', 17, '9781400078776', 28, '2015-04-02', 8, 22, 979, 1, 'Science fiction, Speculative fiction, Psychological Fiction'),
(47, 'The Secret History', 'Donna Tartt', 38, '9781400031702', 2, '2018-12-06', 4, 1, 979, 3, 'Novel, Psychological Fiction'),
(48, 'DA VINCI CODE', 'Dan Brown', 42, '9780274808328', 10, '2017-09-27', 7, 3, 979, 4, 'Novel, Thriller, Mystery, Detective fiction, Crime Fiction, Conspiracy fiction'),
(49, 'White Teeth', 'Zadie Smith', 16, '9780375703867', 20, '2017-12-27', 5, 10, 978, 2, 'Novel, Postcolonial literature, Domestic Fiction'),
(50, 'Breaking Dawn', 'Stephenie Meyer', 26, '9788876250446', 17, '2011-10-01', 6, 7, 978, 1, 'Novel, Romance novel, Young adult fiction, Vampire literature, Paranormal romance');

-- --------------------------------------------------------

--
-- Table structure for table `book_borrowreturn`
--

CREATE TABLE `book_borrowreturn` (
  `borrowId` int(10) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `bookId` int(10) NOT NULL,
  `adminId` int(10) NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `status` varchar(10) NOT NULL,
  `fine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_borrowreturn`
--

INSERT INTO `book_borrowreturn` (`borrowId`, `userId`, `bookId`, `adminId`, `borrowDate`, `returnDate`, `status`, `fine`) VALUES
(1, '4099144697', 35, 7, '2022-12-28', '2023-01-04', 'returned', 0),
(2, '3991344803', 37, 3, '2022-12-28', '2023-01-04', 'pending', 50000),
(3, '3565944741', 5, 9, '2022-12-28', '2023-01-04', 'returned', 0),
(4, '3271744775', 8, 10, '2022-12-28', '2023-01-04', 'returned', 0),
(5, '4008444696', 33, 5, '2022-12-29', '2023-01-05', 'returned', 0),
(6, '3264044652', 45, 2, '2022-12-29', '2023-01-05', 'returned', 0),
(7, '3936444860', 26, 4, '2022-12-30', '2023-01-06', 'pending', 50000),
(8, '4244044808', 31, 8, '2022-12-31', '2023-01-07', 'pending', 50000),
(9, '4123244602', 30, 1, '2022-12-31', '2023-01-07', 'pending', 50000),
(10, '3357244638', 29, 6, '2022-12-31', '2023-01-07', 'pending', 50000),
(11, '3966244768', 36, 6, '2022-12-31', '2023-01-07', 'returned', 0),
(12, '3414544833', 10, 6, '2023-01-01', '2023-01-08', 'returned', 0),
(13, '4048744676', 23, 5, '2023-01-01', '2023-01-08', 'returned', 0),
(14, '3516544562', 17, 4, '2023-01-01', '2023-01-08', 'returned', 0),
(15, '3546144889', 15, 3, '2023-01-02', '2023-01-09', 'pending', 50000),
(16, '4033144798', 14, 9, '2023-01-02', '2023-01-09', 'returned', 0),
(17, '3568944666', 47, 8, '2023-01-02', '2023-01-09', 'returned', 0),
(18, '4112344638', 9, 6, '2023-01-03', '2023-01-10', 'pending', 0),
(19, '3750444644', 34, 1, '2023-01-03', '2023-01-10', 'pending', 0),
(20, '4107944814', 46, 10, '2023-01-03', '2023-01-10', 'pending', 0),
(21, '3111044830', 1, 2, '2023-01-10', '2023-01-17', 'returned', 0),
(22, '3111044830', 10, 2, '2023-01-10', '2023-01-11', 'returned', 0);

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `publishId` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `zip` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`publishId`, `name`, `address`, `email`, `city`, `country`, `zip`) VALUES
(1, 'Bantam Dell Publishing Group', '1745 Broadway New York', 'bbdpublicity@randomhouse.com.', 'New York City', 'United States', '10019'),
(2, 'Simon & Schuster', '1230 Avenue of the Americas', 'SSPublicity@simonandschuster.com', 'New York City', 'United States', '10020-1513'),
(3, 'Hackett Publishing Company', '847 Massachusetts Ave Cambridge', 'customer@hackettpublishing.com', 'Massachusetts', 'United States', '02139-3001'),
(4, 'Houghton Mifflin Harcourt Publishing Company', '125 High Street', 'corporate.communications@hmhco.com', 'Massachusetts', 'United States', '2110'),
(5, 'Doubleday Publishing Company', '1745 Broadway New York', 'knopfpublicity@randomhouse.com', 'New York City', 'United States', '10019'),
(6, 'Longman Pub Group', '1185 Avenue Of The Americas', 'longmanpub@group.com', 'New York City', 'United States', '10036'),
(8, 'CreateSpace Independent Publishing Platform', '618 Maiden Lane Fontana', 'createspace@publishing.com', 'California', 'United States', '92335'),
(9, 'Random House Publishing', '1745 Broadway New York', 'bbdpublicity@randomhouse.com.', 'New York City', 'United States', '10019'),
(10, 'Charles Scribners Sons', '153–157 Fifth Avenue', 'ScribnerPublicity@SimonandSchuster.com', 'New York City', 'United States', '10010'),
(11, 'J. B. Lippincott & Co.', '227 S. 6th Street across from Washington Square', 'permissions@lww.com', 'Philadelphia', 'United States', '19106'),
(16, 'Penguin Random House', '1745 Broadway New York', 'consumerservices@penguinrandomhouse.com', 'New York City', 'United States', '10019'),
(17, 'Harper Perennial', '195 Broadway', 'privacypolicy@harpercollins.com', 'New York City', 'United States', '10007'),
(19, 'Hill and Wang', '77 E 4th St', 'accessibility@macmillanusa.com', 'New York City', 'United States', '10003'),
(20, 'Atria Books', '1230 Avenue of the Americas Rm 13-052a', 'AtriaPublicity@SimonandSchuster.com', 'New York City', 'United States', '10020'),
(23, 'Faber and Faber', 'Bloomsbury House, 74–77 Great Russell Street', 'customer@faber.co.uk', 'London', 'United Kingdom', 'WC1B 3DA'),
(28, 'Chatto & Windus', '20 VAUXHALL BDGE RD', 'customerservice@chattowindus.com', 'London', 'United Kingdom', 'SW1V 2SA'),
(30, 'Back Bay Books', '1271 Avenue of the Americas 11th Floor', 'baybooksmi@gmail.com', 'New York City', 'United States', '10020'),
(32, 'Macmillan Publisher', '120 Broadway', 'macmillan.audio@macmillanusa.com', 'New York City', 'United States', '10271'),
(33, 'Spiegel & Grau', '99 Washington Ave', 'customer@spiegelgrau.com', 'New York City', 'United States', '12210'),
(36, 'Heinemann', '145 Maplewood Avenue, Suite 300', 'custserv@heinemann.com', 'New Hampshire', 'United States', '3801'),
(39, 'Jonathan Cape', '20 VAUXHALL BRIDGE ROAD', 'capesubmissions@randomhouse.co.uk', 'London', 'United Kingdom', 'SW1V 2SA'),
(40, 'Hamish Hamilton', '20 VAUXHALL BRIDGE ROAD', 'hamishhamilton@randomhouse.co.uk', 'London', 'United Kingdom', 'SW1V 2SA'),
(41, 'Headline', 'Carmelite House 50 Victoria Embankment', 'enquiries@hachette.co.uk', 'London', 'United Kingdom', 'EC4Y 0DZ'),
(44, 'St. Martins Press', '120 Broadway', 'specialsales@raincoast.com', 'New York City', 'United States', '10271');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` varchar(10) NOT NULL,
  `userFname` varchar(20) NOT NULL,
  `userLname` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `regisDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userFname`, `userLname`, `birthday`, `email`, `address`, `regisDate`) VALUES
('3111044830', 'Jeffrey', 'Cox', '1985-03-03', 'jeffreycox@mail.com', 'Jl Jend Sudirman Kav 60 Menara Sudirman Dki Jakarta', '2022-09-26'),
('3143044659', 'Nathan', 'Huff', '1986-01-18', 'nathanhuff@mail.com', 'Jl Halimun 39 Guntur', '2022-04-08'),
('3146644677', 'John', 'Garrett', '2001-06-09', 'johngarrett@mail.com', 'Jl Pancoran 22', '2022-04-26'),
('3203544913', 'Ariel', 'Vance', '1987-09-15', 'arielvance@mail.com', 'Jl Mampang Prapatan XI/13', '2022-12-18'),
('3221644796', 'Nancy', 'Saunders', '1988-03-14', 'nancysaunders@mail.com', 'Jl Suci 13 RT 007/04', '2022-08-23'),
('3264044652', 'Kevin', 'Yates', '1989-05-12', 'kevinyates@mail.com', 'Jl Raya Bekasi Km 26 2 Ujung Menteng', '2022-04-01'),
('3271744775', 'Lindsay', 'Wright', '1989-07-28', 'lindsaywright@mail.com', 'Jl Kemang Raya 3-5', '2022-08-02'),
('3306644765', 'Benjamin', 'Mclean', '1990-07-12', 'benjaminmclean@mail.com', 'Jl Arjuna 4 A', '2022-07-23'),
('3309844673', 'Laura', 'Richards', '1990-08-13', 'laurarichards@mail.com', 'Jl Rawamangun Utr II 24 D', '2022-04-22'),
('3310744630', 'Joshua', 'Arellano', '1990-08-22', 'joshuaarellano@mail.com', 'Jl Mayjen DI Panjaitan Kav 40/45', '2022-03-10'),
('3321844720', 'Alicia', 'Ramos', '1990-12-11', 'aliciaramos@mail.com', 'Jl Otto Iskandardinata Raya 121 Bidara Cina', '2022-06-08'),
('3327644815', 'Erin', 'Simmons', '1991-02-07', 'erinsimmons@mail.com', 'Jl Jend A Yani 2 Perk Pulomas Satu Ged 5 Lt 2 Ruang 3 Kayu Putih', '2022-09-11'),
('3343944817', 'Jenny', 'Fischer', '1991-07-20', 'jennyfischer@mail.com', 'Ged Bendungan Hilir', '2022-09-13'),
('3357244638', 'Sonia', 'Mullins', '1991-11-30', 'soniamullins@mail.com', 'Jl H Agus Salim 2 B', '2022-03-18'),
('3366844664', 'Kelly', 'Edwards', '1992-03-05', 'kellyedwards@mail.com', 'Jl Asem Baris Raya 5', '2022-04-13'),
('3374244688', 'Robert', 'Harris', '1992-05-18', 'robertharris@mail.com', 'Jl Raya Cilincing 26 A', '2022-05-07'),
('3414544833', 'Dean', 'Dominguez', '1993-06-25', 'deandominguez@mail.com', 'Jl Gajah Mada 3-5 Kompl Duta Merlin Bl A/46-49', '2022-09-29'),
('3420944646', 'Stephanie', 'Oneill', '1993-08-28', 'stephanieoneill@mail.com', 'Jl Letjen S Parman Kav 12 Wisma Slipi Lt 7 Suite 709', '2022-03-26'),
('3435944918', 'Victoria', 'Fox', '1994-01-25', 'victoriafox@mail.com', 'Ged Bakrie', '2022-12-23'),
('3498244581', 'Rick', 'Richards', '1995-10-10', 'rickrichards@mail.com', 'Jl Kalibaru Tmr 5D 4B RT 002/013', '2022-01-20'),
('3512744710', 'Sabrina', 'Fischer', '1996-03-03', 'sabrinafischer@mail.com', 'Jl RS Fatmawati 15 Golden Plaza Bl D/9 Dki Jakarta', '2022-05-29'),
('3516544562', 'Isabella', 'Edwards', '1996-04-10', 'isabellaedwards@mail.com', 'Jl Manggarai Slt IX 39 D RT 011/01 Bukit Duri', '2022-01-01'),
('3546144889', 'Angela', 'Miller', '1997-01-31', 'angelamiller@mail.com', 'Ged ITC Fatmawati KS', '2022-11-24'),
('3565744803', 'Bryan', 'Willis', '1997-08-15', 'bryanwillis@mail.com', 'Jl Masjid Bendungan 1 13630', '2022-08-30'),
('3565944741', 'Ryan', 'Hall', '1997-08-17', 'ryanhall@mail.com', 'Jl Sentra Primer Baru', '2022-06-29'),
('3568944666', 'Ryan', 'Butler', '1997-09-16', 'ryanbutler@mail.com', 'Jl Jatinegara Brt 72 F', '2022-04-15'),
('3591544753', 'Cassandra', 'Hurley', '1998-04-30', 'cassandrahurley@mail.com', 'Jl Kapuk Kamal 34', '2022-07-11'),
('3623844581', 'Sarah', 'Diaz', '1999-03-19', 'sarahdiaz@mail.com', 'Jl Jend Sudirman Kav 52-53', '2022-01-20'),
('3685344875', 'Olivia', 'Rodriguez', '2000-11-23', 'oliviarodriguez@mail.com', 'Jl Batuceper 89 Kebon Kelapa', '2022-11-10'),
('3729944918', 'Amber', 'Lewis', '2002-02-12', 'amberlewis@mail.com', 'Jl Sentra Primer Baru', '2022-12-23'),
('3750444644', 'Jeanne', 'Atkinson', '2002-09-05', 'jeanneatkinson@mail.com', 'Jl P Jayakarta 14/9-10', '2022-03-24'),
('3764444687', 'Brent', 'Lopez', '2003-01-23', 'brentlopez@mail.com', 'JL Kayu Putih Timur Kav 17413260', '2022-05-06'),
('3936444860', 'Elizabeth', 'Porter', '2007-10-09', 'elizabethporter@mail.com', 'No.31B Jl. H Samali Kalibata kalibata Jakarta selatan 12740', '2022-10-26'),
('3938144871', 'Alan', 'Velez', '2007-10-26', 'alanvelez@mail.com', 'Jl Mangga Dua Raya ITC Mangga Dua Bl B/33', '2022-11-06'),
('3959844644', 'Eric', 'Pena', '2008-05-30', 'ericpena@mail.com', 'Jl Jend Sudirman Kav 52-53', '2022-03-24'),
('3966244768', 'Zachary', 'Collins', '2008-08-02', 'zacharycollins@mail.com', 'Jl Raya Pd Kelapa 7', '2022-07-26'),
('3991344803', 'Steven', 'Swanson', '2009-04-10', 'stevenswanson@mail.com', 'Jl Senen Raya 135', '2022-08-30'),
('4003044728', 'Barry', 'Ross', '2009-08-05', 'barryross@mail.com', 'Jl Raya Cakung Cilincing Gudang East 004', '2022-06-16'),
('4008444696', 'Luis', 'Jacobs', '2009-09-28', 'luisjacobs@mail.com', 'Jl Tanah Abang Tmr 10 Dki Jakarta', '2022-05-15'),
('4033144798', 'John', 'Patton', '2010-06-02', 'johnpatton@mail.com', 'Jl Danau Laut Tawar 61 A', '2022-08-25'),
('4048744676', 'John', 'Murphy', '2010-11-05', 'johnmurphy@mail.com', 'Jl Dr Setiabudi 29', '2022-04-25'),
('4057944660', 'John', 'Yang', '2011-02-05', 'johnyang@mail.com', 'Jl Mangga Dua Raya ITC Mangga Dua Bl C/57', '2022-04-09'),
('4091544699', 'Daniel', 'Morris', '2012-01-07', 'danielmorris@mail.com', 'Jl Raya Pluit Tmr 15', '2022-05-18'),
('4099144697', 'Katherine', 'Holmes', '2012-03-23', 'katherineholmes@mail.com', 'Jl Patra Raya 1 D RT 008/08 Dki Jakarta', '2022-05-16'),
('4107944814', 'Mario', 'Stewart', '2012-06-19', 'mariostewart@mail.com', 'Jl Sakti II/26', '2022-09-10'),
('4112344638', 'Danielle', 'Cruz', '2012-08-02', 'daniellecruz@mail.com', 'Mal Ciputra LG-I/13', '2022-03-18'),
('4123244602', 'Nathan', 'Compton', '2012-11-19', 'nathancompton@mail.com', 'Psr Tanah Abang Bl A/23', '2022-02-10'),
('4244044808', 'Lauren', 'Barnes', '2016-03-11', 'laurenbarnes@mail.com', 'Jl RS Fatmawati 74 Jl RS Fatmawati 74', '2022-09-04'),
('4261244644', 'Chad', 'Hall', '2016-08-30', 'chadhall@mail.com', 'JL. Mega Kuningan Lot 5.1 Menara Rajawali Lt 27 12950', '2022-03-24'),
('4262544917', 'Courtney', 'Morton', '2016-09-12', 'courtneymorton@mail.com', 'Jl Jend Sudirman Kav 61-62 Summitmas Ii Lt 15', '2022-12-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookId`);

--
-- Indexes for table `book_borrowreturn`
--
ALTER TABLE `book_borrowreturn`
  ADD PRIMARY KEY (`borrowId`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`publishId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `userId` (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `bookId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `book_borrowreturn`
--
ALTER TABLE `book_borrowreturn`
  MODIFY `borrowId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `publishId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
