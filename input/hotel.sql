/*
Navicat MariaDB Data Transfer

Source Server         : root
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : finalproject_release1

Target Server Type    : MariaDB
Target Server Version : 50550
File Encoding         : 65001

Date: 2016-11-13 23:50:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `hotelId` int(255) NOT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `streetAddress` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hotelId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('891239', 'Country Hearth Inn San Francisco Hotel', '2707 Lombard St', 'San Francisco', 'CA', '37.798605', '-122.446239', 'USA');
INSERT INTO `hotel` VALUES ('455591', 'Days Inn San Francisco International Airport West', '1550 El Camino Real', 'San Bruno', 'CA', '37.612173', '-122.403153', 'USA');
INSERT INTO `hotel` VALUES ('14451', 'King George Hotel', '334 Mason Street', 'San Francisco', 'CA', '37.786775', '-122.409806', 'USA');
INSERT INTO `hotel` VALUES ('41833', 'San Remo Hotel', '2237 Mason Street', 'San Francisco', 'CA', '37.80427', '-122.413138', 'USA');
INSERT INTO `hotel` VALUES ('17280', 'Four Points by Sheraton San Francisco Bay Bridge', '1603 Powell St', 'Emeryville', 'CA', '37.838623', '-122.293758', 'USA');
INSERT INTO `hotel` VALUES ('22510', 'Hotel Whitcomb', '1231 Market St', 'San Francisco', 'CA', '37.778345', '-122.415247', 'USA');
INSERT INTO `hotel` VALUES ('790530', 'Ramada Limited San Francisco Airport North', '721 Airport Blvd', 'South San Francisco', 'CA', '37.65913', '-122.40557', 'USA');
INSERT INTO `hotel` VALUES ('15824334', 'Hostal San Francisco', 'Cdla. Profesores Aguirre Abad', 'Guayaquil', 'Guayas', '-2.174839', '-79.88918', 'ECU');
INSERT INTO `hotel` VALUES ('897225', 'Homewood Suites by Hilton San Francisco Airport North', '2000 Shoreline Ct', 'Brisbane', 'CA', '37.671754', '-122.388151', 'USA');
INSERT INTO `hotel` VALUES ('8647196', 'Hampton Inn San Francisco Downtown/Convention Center', '942 Mission Street', 'San Francisco', 'CA', '37.781945', '-122.407533', 'USA');
INSERT INTO `hotel` VALUES ('476728', 'Pier 2620 Hotel Fisherman\'s Wharf', '2620 Jones Street', 'San Francisco', 'CA', '37.805388', '-122.416926', 'USA');
INSERT INTO `hotel` VALUES ('1694', 'Wyndham Canterbury at San Francisco', '750 Sutter Street', 'San Francisco', 'CA', '37.788677', '-122.412614', 'USA');
INSERT INTO `hotel` VALUES ('11509', 'The Donatello Hotel', '501 Post St', 'San Francisco', 'CA', '37.788062', '-122.410188', 'USA');
INSERT INTO `hotel` VALUES ('1146383', 'Americas Best Value Inn & Suites - Mill Valley/San Francisco', '155 Shoreline Hwy', 'Mill Valley', 'CA', '37.88088', '-122.51932', 'USA');
INSERT INTO `hotel` VALUES ('28502', 'Crowne Plaza San Francisco Airport', '1177 Airport Blvd', 'Burlingame', 'CA', '37.591072', '-122.36011', 'USA');
INSERT INTO `hotel` VALUES ('7827145', 'Hotel San Francisco', 'Av. Universidad 375 esq. Zaragoza', 'San Luis Potosi', 'SLP', '22.149778', '-100.975533', 'MEX');
INSERT INTO `hotel` VALUES ('360', 'Courtyard by Marriott San Francisco Union Square', '761 Post Street', 'San Francisco', 'CA', '37.787489', '-122.414263', 'USA');
INSERT INTO `hotel` VALUES ('9613947', 'Apartamentos San Francisco', 'C/ Dragonera s/n.', 'Sant Antoni de Portmany', 'Ibiza', '38.983686', '1.300549', 'ESP');
INSERT INTO `hotel` VALUES ('890830', 'Francisco Bay Inn', '1501 Lombard St', 'San Francisco', 'CA', '37.801104', '-122.426187', 'USA');
INSERT INTO `hotel` VALUES ('854854', 'Monasterio de San Francisco', 'Avenida Pio XII, 35', 'Palma del Rio', 'Cordoba', '37.694132', '-5.280605', 'ESP');
INSERT INTO `hotel` VALUES ('487', 'Holiday Inn San Francisco International Airport', '275 S Airport Blvd', 'South San Francisco', 'CA', '37.64682', '-122.40551', 'USA');
INSERT INTO `hotel` VALUES ('17616', 'Kensington Park Hotel', '450 Post St', 'San Francisco', 'CA', '37.788238', '-122.409353', 'USA');
INSERT INTO `hotel` VALUES ('5043', 'Quality Inn San Francisco', 'Victoria 409 Col Centro', 'Chihuahua', 'CHIH', '28.63512', '-106.077574', 'MEX');
INSERT INTO `hotel` VALUES ('200649', 'Extended Stay America San Francisco-San Carlos', '3 Circle Star Way', 'San Carlos', 'CA', '37.497987', '-122.240608', 'USA');
INSERT INTO `hotel` VALUES ('5045', 'Hotel Vertigo', '940 Sutter Street', 'San Francisco', 'CA', '37.78835', '-122.41582', 'USA');
INSERT INTO `hotel` VALUES ('12493', 'Serrano Hotel', '405 Taylor St', 'San Francisco', 'CA', '37.786182', '-122.411366', 'USA');
INSERT INTO `hotel` VALUES ('491', 'Park Central Hotel San Francisco, a Starwood Hotel', '50 3rd St', 'San Francisco', 'CA', '37.78681', '-122.40261', 'USA');
INSERT INTO `hotel` VALUES ('894295', 'Comfort Inn & Suites San Francisco Airport West', '611 San Bruno Avenue East', 'San Bruno', 'CA', '37.630876', '-122.407668', 'USA');
INSERT INTO `hotel` VALUES ('578899', 'Holiday Inn Express and Suites Fisherman\'s Wharf', '550 N Point St', 'San Francisco', 'CA', '37.80625', '-122.416214', 'USA');
INSERT INTO `hotel` VALUES ('1442078', 'San Francisco Hotel Monumento', 'Campillo de San Francisco, 3', 'Santiago de Compostela', 'La Coruna', '42.882885', '-8.545795', 'ESP');
INSERT INTO `hotel` VALUES ('1793764', 'San Francisco Business Class', 'Portal Carrillo Puerto No 1, Centro', 'Irapuato', 'GTO', '20.674505', '-101.346978', 'MEX');
INSERT INTO `hotel` VALUES ('283107', 'Super 8 San Bruno/San Francisco Airport', '421 El Camino Real', 'San Bruno', 'CA', '37.621968', '-122.412012', 'USA');
INSERT INTO `hotel` VALUES ('23383', 'Taj Campton Place', '340 Stockton St', 'San Francisco', 'CA', '37.789103', '-122.4069', 'USA');
INSERT INTO `hotel` VALUES ('15674041', 'Hyatt Place Emeryville/San Francisco Bay Area', '5700 Bay Street', 'Emeryville', 'CA', '37.836369', '-122.29235', 'USA');
INSERT INTO `hotel` VALUES ('5830', 'Sofitel San Francisco Bay', '223 Twin Dolphin Dr', 'Redwood City', 'CA', '37.522707', '-122.261983', 'USA');
INSERT INTO `hotel` VALUES ('14553', 'San Francisco Inn', '385 9th St', 'San Francisco', 'CA', '37.77273', '-122.41028', 'USA');
INSERT INTO `hotel` VALUES ('1813658', 'Hotel San Francisco', 'Av Central Sur No 94', 'Tapachula', 'CHIS', '14.900616', '-92.26703', 'MEX');
INSERT INTO `hotel` VALUES ('15769', 'The Marker San Francisco, a Joie de Vivre Hotel', '501 Geary St', 'San Francisco', 'CA', '37.78698', '-122.41171', 'USA');
INSERT INTO `hotel` VALUES ('791769', 'Four Seasons Hotel San Francisco', '757 Market St', 'San Francisco', 'CA', '37.786626', '-122.404719', 'USA');
INSERT INTO `hotel` VALUES ('225819', 'Hotel Zelos San Francisco', '12 4th St.', 'San Francisco', 'CA', '37.785398', '-122.40539', 'USA');
INSERT INTO `hotel` VALUES ('23838', 'San Francisco Airport Marriott Waterfront', '1800 Old Bayshore Hwy', 'Burlingame', 'CA', '37.60169', '-122.370208', 'USA');
INSERT INTO `hotel` VALUES ('3552', 'Best Western Plus The Tuscan', '425 North Point', 'San Francisco', 'CA', '37.806542', '-122.414058', 'USA');
INSERT INTO `hotel` VALUES ('42336', 'Embassy Suites San Francisco Airport - Waterfront', '150 Anza Blvd', 'Burlingame', 'CA', '37.591192', '-122.347264', 'USA');
INSERT INTO `hotel` VALUES ('22500', 'Hyatt Centric Fisherman\'s Wharf San Francisco', '555 N Point St', 'San Francisco', 'CA', '37.80628', '-122.41641', 'USA');
INSERT INTO `hotel` VALUES ('2959920', 'Hotel Posada San Francisco Tlaxcala', 'Plaza de La Constitucion No 17', 'Tlaxcala', 'TLAX', '19.31659', '-98.23898', 'MEX');
INSERT INTO `hotel` VALUES ('14742075', '673 San Francisco Avenue', '673 San Francisco Avenue', 'South Lake Tahoe', 'CA', '38.939527', '-119.991128', 'USA');
INSERT INTO `hotel` VALUES ('897333', 'Hampton Inn & Suites San Francisco Airport South-Burlingame', '1755 Bayshore Hwy', 'Burlingame', 'CA', '37.602151', '-122.371307', 'USA');
INSERT INTO `hotel` VALUES ('5423384', 'San Francisco Apartments', 'Punta Paitilla, Calle Ramón H Jurado', 'Panama City', 'Panama', '8.97568', '-79.52165', 'PAN');
INSERT INTO `hotel` VALUES ('2458097', 'Hotel San Francisco', 'Carrera 10 No. 23-63', 'Bogota', 'Distrito Capital', '4.610412', '-74.071839', 'COL');
INSERT INTO `hotel` VALUES ('1006', 'Hotel Plaza San Francisco', 'Alameda 816', 'Santiago', 'Region Metropolitana', '-33.443493', '-70.648486', 'CHL');
INSERT INTO `hotel` VALUES ('2336', 'Holiday Inn Express San Francisco Airport South', '1250 Bayshore Hwy', 'Burlingame', 'CA', '37.592239', '-122.362652', 'USA');
INSERT INTO `hotel` VALUES ('1003', 'Hotel Kabuki - a Joie de Vivre Boutique Hotel', '1625 Post St', 'San Francisco', 'CA', '37.785665', '-122.428678', 'USA');
INSERT INTO `hotel` VALUES ('23395', 'Hotel Union Square', '114 Powell St', 'San Francisco', 'CA', '37.785837', '-122.407888', 'USA');
INSERT INTO `hotel` VALUES ('2530359', 'San Francisco', 'Viale Carducci 68', 'Viareggio', 'LU', '43.878751', '10.236779', 'ITA');
INSERT INTO `hotel` VALUES ('828', 'DoubleTree by Hilton San Francisco Airport', '835 Airport Blvd', 'Burlingame', 'CA', '37.59113', '-122.35041', 'USA');
INSERT INTO `hotel` VALUES ('3308', 'CitiGarden Hotel', '245 S Airport Blvd', 'South San Francisco', 'CA', '37.64798', '-122.40552', 'USA');
INSERT INTO `hotel` VALUES ('8151107', 'Mediterranean Dreams San Francisco', 'Av 5c Sur con Calle 72 Este, Casa # 94', 'Panama City', 'Panama', '8.989748', '-79.507239', 'PAN');
INSERT INTO `hotel` VALUES ('5361249', 'Hotel San Francisco', 'Carrera 3 No 9-50', 'Santa Marta', 'Magdalena', '11.203319', '-74.225873', 'COL');
INSERT INTO `hotel` VALUES ('1321986', 'The St. Regis San Francisco', '125 3rd St', 'San Francisco', 'CA', '37.78596', '-122.40158', 'USA');
INSERT INTO `hotel` VALUES ('1543026', 'San Francisco Plaza Hotel', 'Degollado 267', 'Guadalajara', 'JAL', '20.672613', '-103.345367', 'MEX');
INSERT INTO `hotel` VALUES ('599536', 'DoubleTree by Hilton Hotel San Francisco Airport North', '5000 Sierra Point Pkwy', 'Brisbane', 'CA', '37.673436', '-122.387268', 'USA');
INSERT INTO `hotel` VALUES ('118232', 'Extended Stay America San Francisco - San Mateo - SFO', '1830 Gateway Dr', 'San Mateo', 'CA', '37.557152', '-122.279296', 'USA');
INSERT INTO `hotel` VALUES ('1145267', 'Inn San Francisco', '943 South Van Ness Avenue', 'San Francisco', 'CA', '37.75798', '-122.416792', 'USA');
INSERT INTO `hotel` VALUES ('912982', 'Club Quarters Hotel in San Francisco', '424 Clay St', 'San Francisco', 'CA', '37.794972', '-122.400809', 'USA');
INSERT INTO `hotel` VALUES ('790579', 'Four Points by Sheraton Hotel & Suites San Francisco Airport', '264 South Airport Blvd.', 'South San Francisco', 'CA', '37.647312', '-122.40557', 'USA');
INSERT INTO `hotel` VALUES ('16955', 'Travelodge San Francisco Airport-North', '326 S Airport Blvd', 'South San Francisco', 'CA', '37.645129', '-122.40492', 'USA');
INSERT INTO `hotel` VALUES ('3960243', 'San Francisco Plaza Hotel', 'Calle Ceniza 147', 'Cusco', 'Cusco', '-13.51757', '-71.984254', 'PER');
INSERT INTO `hotel` VALUES ('1110785', 'Bay Landing San Francisco Airport Hotel', '1550 Bayshore Hwy', 'Burlingame', 'CA', '37.597986', '-122.36624', 'USA');
INSERT INTO `hotel` VALUES ('6271901', 'Stanford Court San Francisco', '905 California Street', 'San Francisco', 'CA', '37.791969', '-122.409717', 'USA');
INSERT INTO `hotel` VALUES ('14781', 'Americas Best Value Inn - San Carlos/San Francisco', '1562 El Camino Real', 'San Carlos', 'CA', '37.49606', '-122.247282', 'USA');
INSERT INTO `hotel` VALUES ('9744315', 'Hacienda San Francisco', 'San Francisco Manzanilla', 'Dzidzantun', 'YUC', '21.251914', '-89.023959', 'MEX');
INSERT INTO `hotel` VALUES ('24468', 'San Francisco Marriott Union Square', '480 Sutter Street', 'San Francisco', 'CA', '37.78928', '-122.40829', 'USA');
INSERT INTO `hotel` VALUES ('20547', 'Chancellor Hotel on Union Square', '433 Powell St', 'San Francisco', 'CA', '37.78872', '-122.408538', 'USA');
INSERT INTO `hotel` VALUES ('12239317', 'Amazing Home in San Francisco Bay', '1508 Magnolia Street', 'Oakland', 'CA', '37.81064', '-122.286906', 'USA');
INSERT INTO `hotel` VALUES ('26760', 'The Westin St Francis San Francisco on Union Square', '335 Powell Street', 'San Francisco', 'CA', '37.78773', '-122.40822', 'USA');
INSERT INTO `hotel` VALUES ('662368', 'Omni San Francisco Hotel', '500 California St', 'San Francisco', 'CA', '37.792928', '-122.402712', 'USA');
INSERT INTO `hotel` VALUES ('5984', 'Comfort Inn by the Bay', '2775 Van Ness Ave', 'San Francisco', 'CA', '37.801192', '-122.425195', 'USA');
INSERT INTO `hotel` VALUES ('808403', 'Fairfield Inn & Suites by Marriott San Francisco Airport', '250 El Camino Real', 'Millbrae', 'CA', '37.601382', '-122.390502', 'USA');
INSERT INTO `hotel` VALUES ('21073', 'Clift', '495 Geary St', 'San Francisco', 'CA', '37.786954', '-122.411433', 'USA');
INSERT INTO `hotel` VALUES ('9520298', 'Hotel San Francisco Inn', 'Calle San Francisco 207', 'Arequipa', 'Arequipa', '-16.397197', '-71.535709', 'PER');
INSERT INTO `hotel` VALUES ('1515923', 'Orchard Garden Hotel', '466 Bush St', 'San Francisco', 'CA', '37.790679', '-122.405191', 'USA');
INSERT INTO `hotel` VALUES ('5883', 'Grand Hyatt San Francisco Union Square', '345 Stockton St', 'San Francisco', 'CA', '37.78911', '-122.40682', 'USA');
INSERT INTO `hotel` VALUES ('910225', 'Americas Best Value Inn & Suites-San Francisco/Golden Gate', '2322 Lombard St', 'San Francisco', 'CA', '37.799383', '-122.43977', 'USA');
INSERT INTO `hotel` VALUES ('4993138', 'San Francisco Spiaggia', 'Viale Regina Margherita 42', 'Rimini', 'RN', '44.038163', '12.613343', 'ITA');
INSERT INTO `hotel` VALUES ('23581', 'Axiom Hotel San Francisco', '28 Cyril Magnin Street', 'San Francisco', 'CA', '37.78488', '-122.408599', 'USA');
INSERT INTO `hotel` VALUES ('4302', 'Hilton San Francisco Downtown/Financial District', '750 Kearny St', 'San Francisco', 'CA', '37.79503', '-122.40486', 'USA');
INSERT INTO `hotel` VALUES ('5875', 'Hotel Zeppelin San Francisco', '545 Post St', 'San Francisco', 'CA', '37.78803', '-122.41079', 'USA');
INSERT INTO `hotel` VALUES ('20191', 'Courtyard San Francisco Larkspur Landing/Marin County', '2500 Larkspur Landing Cir', 'Larkspur', 'CA', '37.94774', '-122.50803', 'USA');
INSERT INTO `hotel` VALUES ('18437', 'Hotel Mark Twain', '345 Taylor St', 'San Francisco', 'CA', '37.785712', '-122.411283', 'USA');
INSERT INTO `hotel` VALUES ('1606984', 'Extended Stay America San Rafael - Francisco Boulevard East', '1775 Francisco Blvd', 'San Rafael', 'CA', '37.94922', '-122.49359', 'USA');
INSERT INTO `hotel` VALUES ('18204', 'Holiday Inn Civic Center San Francisco', '50 8th St', 'San Francisco', 'CA', '37.77814', '-122.41392', 'USA');
INSERT INTO `hotel` VALUES ('18320', 'Hotel Zetta San Francisco', '55 5th St', 'San Francisco', 'CA', '37.78336', '-122.40712', 'USA');
INSERT INTO `hotel` VALUES ('18200', 'The Fairmont San Francisco', '950 Mason Street Atop Nob Hill', 'San Francisco', 'CA', '37.79229', '-122.41088', 'USA');
INSERT INTO `hotel` VALUES ('26500', 'Hyatt Regency San Francisco Airport', '1333 Bayshore Hwy', 'Burlingame', 'CA', '37.593824', '-122.364268', 'USA');
INSERT INTO `hotel` VALUES ('693658', 'Marriott Courtyard San Francisco Downtown', '299 2nd St', 'San Francisco', 'CA', '37.785816', '-122.396965', 'USA');
INSERT INTO `hotel` VALUES ('444672', 'Monarch Hotel', '1015 Geary St', 'San Francisco', 'CA', '37.785856', '-122.420091', 'USA');
INSERT INTO `hotel` VALUES ('22148', 'The Ritz-Carlton, San Francisco', '600 Stockton St', 'San Francisco', 'CA', '37.791712', '-122.407436', 'USA');
INSERT INTO `hotel` VALUES ('522505', 'Hotel Focus SFO', '111 Mitchell Ave', 'South San Francisco', 'CA', '37.649572', '-122.405505', 'USA');
INSERT INTO `hotel` VALUES ('27274', 'Palace Hotel, a Luxury Collection Hotel, San Francisco', '2 New Montgomery St', 'San Francisco', 'CA', '37.788682', '-122.401919', 'USA');
INSERT INTO `hotel` VALUES ('150946', 'Holiday Inn Express San Francisco Union Square', '235 O Farrell Street', 'San Francisco', 'CA', '37.786346', '-122.408667', 'USA');
INSERT INTO `hotel` VALUES ('1047', 'Holiday Inn San Francisco-Fisherman\'s Wharf', '1300 Columbus Ave', 'San Francisco', 'CA', '37.80603', '-122.41844', 'USA');
INSERT INTO `hotel` VALUES ('1139289', 'Americas Best Value Inn & Suites-San Francisco Airport North', '701 Airport Blvd', 'South San Francisco', 'CA', '37.65838', '-122.405964', 'USA');
INSERT INTO `hotel` VALUES ('9491356', 'Green Tortoise Hostel San Francisco', '494 Broadway', 'San Francisco', 'CA', '37.798042', '-122.405175', 'USA');
INSERT INTO `hotel` VALUES ('12345', 'Hilton San Francisco Airport Bayfront', '600 Airport Blvd', 'Burlingame', 'CA', '37.58948', '-122.34343', 'USA');
INSERT INTO `hotel` VALUES ('7942', 'Days Inn Novato/San Francisco', '8141 Redwood Blvd', 'Novato', 'CA', '38.133303', '-122.564154', 'USA');
INSERT INTO `hotel` VALUES ('9329', 'La Quinta Inn San Francisco Airport North', '20 Airport Blvd', 'South San Francisco', 'CA', '37.6501', '-122.408321', 'USA');
INSERT INTO `hotel` VALUES ('51116', 'The Marina Inn on San Francisco Bay', '68 Monarch Bay Dr', 'San Leandro', 'CA', '37.697421', '-122.188022', 'USA');
INSERT INTO `hotel` VALUES ('296720', 'Hilton Garden Inn San Francisco Airport North', '670 Gateway Blvd', 'South San Francisco', 'CA', '37.657933', '-122.398376', 'USA');
INSERT INTO `hotel` VALUES ('25622', 'Hilton San Francisco Union Square', '333 O\'Farrell St.', 'San Francisco', 'CA', '37.78616', '-122.41018', 'USA');
INSERT INTO `hotel` VALUES ('918527', 'Extended Stay America San Francisco - Belmont', '120 Sem Ln', 'Belmont', 'CA', '37.524636', '-122.26685', 'USA');
INSERT INTO `hotel` VALUES ('904482', 'Americas Best Value Inn, San Francisco/Pacifica', '2160 Francisco Blvd', 'Pacifica', 'CA', '37.633118', '-122.490015', 'USA');
INSERT INTO `hotel` VALUES ('4564720', 'Hotel San Francisco', 'Plaza Mayor 6', 'Villafranca del Bierzo', 'LEON', '42.606098', '-6.809356', 'ESP');
INSERT INTO `hotel` VALUES ('437098', 'Hampton Inn San Francisco-Airport', '300 Gateway Blvd', 'South San Francisco', 'CA', '37.656733', '-122.400771', 'USA');
INSERT INTO `hotel` VALUES ('25860', 'Hotel Carlton, a Joie de Vivre Boutique Hotel', '1075 Sutter St', 'San Francisco', 'CA', '37.78807', '-122.41809', 'USA');
INSERT INTO `hotel` VALUES ('876', 'The Warwick San Francisco', '490 Geary St', 'San Francisco', 'CA', '37.78703', '-122.41136', 'USA');
INSERT INTO `hotel` VALUES ('7713', 'Days Inn San Francisco Lombard', '2358 Lombard St', 'San Francisco', 'CA', '37.79933', '-122.44036', 'USA');
INSERT INTO `hotel` VALUES ('6505', 'Loews Regency San Francisco', '222 Sansome St', 'San Francisco', 'CA', '37.792535', '-122.400929', 'USA');
INSERT INTO `hotel` VALUES ('789395', 'Adante Hotel, a C-Two Hotel', '610 Geary St', 'San Francisco', 'CA', '37.786724', '-122.413611', 'USA');
INSERT INTO `hotel` VALUES ('438727', 'Quality Inn & Suites South San Francisco', '410 S Airport Blvd', 'South San Francisco', 'CA', '37.642511', '-122.403311', 'USA');
INSERT INTO `hotel` VALUES ('7262522', 'San Francisco Flat', 'Av. Alvares Cabral, 967', 'Belo Horizonte', 'MG', '-19.929104', '-43.943349', 'BRA');
INSERT INTO `hotel` VALUES ('7825812', 'San Francisco Plazza Hotel', 'Rua Marques de Itu, 271', 'Sao Paulo', 'SP', '-23.543838', '-46.646928', 'BRA');
INSERT INTO `hotel` VALUES ('856888', 'Holiday Inn Express San Francisco-Airport North', '373 S Airport Blvd', 'South San Francisco', 'CA', '37.643498', '-122.403902', 'USA');
INSERT INTO `hotel` VALUES ('533579', 'Hotel San Francisco', 'Ignacio Lopez Rayon 104 Sur Co', 'Toluca', 'MEX', '19.27604', '-99.64702', 'MEX');
INSERT INTO `hotel` VALUES ('24540', 'Hotel G San Francisco', '386 Geary Street', 'San Francisco', 'CA', '37.787207', '-122.409467', 'USA');
INSERT INTO `hotel` VALUES ('5425', 'Villa Florence', '225 Powell St', 'San Francisco', 'CA', '37.786865', '-122.408155', 'USA');
INSERT INTO `hotel` VALUES ('3918508', 'Hostal San Francisco', 'Calle Gonzalez De Leon 4', 'Seville', 'Seville', '37.386996', '-5.985685', 'ESP');
INSERT INTO `hotel` VALUES ('287112', 'W San Francisco', '181 3rd St', 'San Francisco', 'CA', '37.785148', '-122.400555', 'USA');
INSERT INTO `hotel` VALUES ('1588312', 'Knights Inn San Francisco / Near the Presidio', '1 Richardson Ave', 'San Francisco', 'CA', '37.79892', '-122.44489', 'USA');
INSERT INTO `hotel` VALUES ('8090040', 'Hotel San Francisco', 'Av. Carlos Barbosa, 974', 'Porto Alegre', 'RS', '-30.064147', '-51.212147', 'BRA');
INSERT INTO `hotel` VALUES ('19842', 'Days Inn San Francisco Downtown/Civic Center Area', '465 Grove Street', 'San Francisco', 'CA', '37.777681', '-122.424157', 'USA');
INSERT INTO `hotel` VALUES ('40443', 'Vagabond Inn Executive San Francisco Airport', '1640 Old Bayshore Hwy', 'Burlingame', 'CA', '37.599815', '-122.367883', 'USA');
INSERT INTO `hotel` VALUES ('1208461', 'Super 8 Union Square San Francisco', '415 O Farrell Street', 'San Francisco', 'CA', '37.785966', '-122.411717', 'USA');
INSERT INTO `hotel` VALUES ('9043627', 'Apartamentos San Francisco', 'San Francisco, 335', 'Santiago', 'Region Metropolitana', '-33.449181', '-70.647001', 'CHL');
INSERT INTO `hotel` VALUES ('40682', 'Travelodge Central San Francisco', '1707 Market St', 'San Francisco', 'CA', '37.772402', '-122.422653', 'USA');
INSERT INTO `hotel` VALUES ('480813', 'Hotel San Francisco Leon', 'Blvd Adolfo Lopez Mateos 2715', 'Leon', 'GTO', '21.10962', '-101.64514', 'MEX');
INSERT INTO `hotel` VALUES ('12539', 'Parc 55 San Francisco - A Hilton Hotel', '55 Cyril Magnin St', 'San Francisco', 'CA', '37.78458', '-122.40854', 'USA');
INSERT INTO `hotel` VALUES ('4432027', 'Hotel Brisas de San Francisco', 'Calle 56 Bolivia No. 41-114', 'Medellin', null, '6.251009', '-75.560138', 'COL');
INSERT INTO `hotel` VALUES ('524164', 'Residence Inn by Marriott San Francisco Airport at Oyster Pt', '1350 Veterans Way', 'South San Francisco', 'CA', '37.666642', '-122.394261', 'USA');
INSERT INTO `hotel` VALUES ('919198', 'The Opal San Francisco', '1050 Van Ness Avenue', 'San Francisco', 'CA', '37.785446', '-122.421273', 'USA');
INSERT INTO `hotel` VALUES ('15135', 'Days Inn South San Francisco - Oyster Point / Airport', '1113 Airport Blvd', 'South San Francisco', 'CA', '37.66333', '-122.40086', 'USA');
INSERT INTO `hotel` VALUES ('15254', 'Sir Francis Drake, a Kimpton Hotel', '450 Powell Street', 'San Francisco', 'CA', '37.788911', '-122.40857', 'USA');
INSERT INTO `hotel` VALUES ('2391910', 'Hotel San Francisco', 'Calle de Maria Cabrera, 18', 'Ronda', 'Malaga', '36.743533', '-5.162816', 'ESP');
INSERT INTO `hotel` VALUES ('1766605', 'InterContinental San Francisco', '888 Howard Street', 'San Francisco', 'CA', '37.78171', '-122.4047', 'USA');
INSERT INTO `hotel` VALUES ('2063', 'JW Marriott San Francisco Union Square', '500 Post Street', 'San Francisco', 'CA', '37.788058', '-122.410261', 'USA');
INSERT INTO `hotel` VALUES ('26945', 'La Quinta Inn & Suites San Francisco Airport West', '1390 El Camino Real', 'Millbrae', 'CA', '37.610082', '-122.401102', 'USA');
INSERT INTO `hotel` VALUES ('12239224', 'Fabulous Home in San Francisco Bay', '1508 Magnolia', 'Oakland', 'CA', '37.810651', '-122.286927', 'USA');
INSERT INTO `hotel` VALUES ('57255', 'da Vinci Villa', '2550 Van Ness Ave', 'San Francisco', 'CA', '37.799447', '-122.424086', 'USA');
INSERT INTO `hotel` VALUES ('28200', 'Red Roof Plus San Francisco Airport', '777 Airport Blvd', 'Burlingame', 'CA', '37.59003', '-122.34934', 'USA');
INSERT INTO `hotel` VALUES ('23406', 'Hotel Nikko San Francisco', '222 Mason St', 'San Francisco', 'CA', '37.785683', '-122.409575', 'USA');
INSERT INTO `hotel` VALUES ('5342', 'Hotel San Francisco Centro Histórico', 'Luis Moya N 11 Col Centro', 'Mexico City', 'DF', '19.43408', '-99.1449', 'MEX');
INSERT INTO `hotel` VALUES ('16552', 'Holiday Inn Express Mill Valley San Francisco Area', '160 Shoreline Hwy', 'Mill Valley', 'CA', '37.88088', '-122.51933', 'USA');
INSERT INTO `hotel` VALUES ('876315', 'Hotel Fusion, a C-Two Hotel', '140 Ellis Street', 'San Francisco', 'CA', '37.785401', '-122.408676', 'USA');
INSERT INTO `hotel` VALUES ('8121405', 'Hotel Villa San Francisco', 'paseo ramon corona #16', 'Chapala', 'JAL', '20.287707', '-103.192051', 'MEX');
INSERT INTO `hotel` VALUES ('14772741', 'USA Hostels San Francisco', '711 Post Street', 'San Francisco', 'CA', '37.787609', '-122.413856', 'USA');
INSERT INTO `hotel` VALUES ('2981', 'Embassy Suites San Francisco Airport - South San Francisco', '250 Gateway Blvd', 'South San Francisco', 'CA', '37.655776', '-122.401654', 'USA');
INSERT INTO `hotel` VALUES ('7635', 'Best Western Plus Grosvenor Airport Hotel', '380 S Airport Boulevard', 'South San Francisco', 'CA', '37.6435', '-122.40383', 'USA');
INSERT INTO `hotel` VALUES ('6666', 'Handlery Union Square Hotel', '351 Geary St', 'San Francisco', 'CA', '37.78733', '-122.40902', 'USA');
INSERT INTO `hotel` VALUES ('50993', 'Motel 6 San Francisco Belmont', '1101 Shoreway Rd', 'Belmont', 'CA', '37.525106', '-122.268015', 'USA');
INSERT INTO `hotel` VALUES ('5338', 'Executive Hotel Vintage Court', '650 Bush Street', 'San Francisco', 'CA', '37.79033', '-122.40806', 'USA');
INSERT INTO `hotel` VALUES ('6908747', 'Camp San Francisco', 'Sadashiv Bhatt, Opp.Palolem Dental Clin', 'Canacona', 'Goa', '15.011816', '74.021522', 'IND');
INSERT INTO `hotel` VALUES ('6706717', 'San Francisco Apts Fa', 'Avenida Europa 23', 'Benidorm', 'Alicante', '38.542113', '-0.120327', 'ESP');
INSERT INTO `hotel` VALUES ('1155453', 'San Francisco Hotel', 'Viale Lombardia 55', 'Milan', 'MI', '45.48552', '9.22345', 'ITA');
INSERT INTO `hotel` VALUES ('287665', 'Larkspur Landing South San Francisco - An All-Suite Hotel', '690 Gateway Blvd', 'South San Francisco', 'CA', '37.658668', '-122.39756', 'USA');
INSERT INTO `hotel` VALUES ('7655', 'Staybridge Suites San Francisco Airport', '1350 Huntington Ave', 'San Bruno', 'CA', '37.64146', '-122.419766', 'USA');
INSERT INTO `hotel` VALUES ('19035', 'Howard Johnson San Francisco Marina District', '1940 Lombard St', 'San Francisco', 'CA', '37.800269', '-122.433465', 'USA');
INSERT INTO `hotel` VALUES ('23774', 'Hyatt Regency San Francisco', '5 Embarcadero Center', 'San Francisco', 'CA', '37.79372', '-122.39644', 'USA');
INSERT INTO `hotel` VALUES ('24620', 'InterContinental Mark Hopkins', '999 California Street', 'San Francisco', 'CA', '37.791865', '-122.410716', 'USA');
INSERT INTO `hotel` VALUES ('20701', 'Comfort Inn and Suites San Francisco Airport North', '121 E Grand Ave', 'South San Francisco', 'CA', '37.654433', '-122.404861', 'USA');
INSERT INTO `hotel` VALUES ('24625', 'Sheraton Fisherman\'s Wharf Hotel', '2500 Mason St', 'San Francisco', 'CA', '37.80688', '-122.4138', 'USA');
INSERT INTO `hotel` VALUES ('25271', 'Courtyard by Marriott San Francisco Airport', '1050 Bayhill Dr', 'San Bruno', 'CA', '37.63011', '-122.41925', 'USA');
INSERT INTO `hotel` VALUES ('12671477', 'Hotel Casa San Francisco', 'Calle Corral #207', 'Granada', null, '11.932603', '-85.952882', 'NIC');
INSERT INTO `hotel` VALUES ('855749', 'Americas Best Value Inn-Richmond/San Francisco', '1598 Carlson Blvd', 'Richmond', 'CA', '37.91045', '-122.3163', 'USA');
INSERT INTO `hotel` VALUES ('11793', 'The Pickwick Hotel', '85 5th St', 'San Francisco', 'CA', '37.78296', '-122.40669', 'USA');
INSERT INTO `hotel` VALUES ('8737', 'San Francisco Marriott Marquis Union Square', '780 Mission Street', 'San Francisco', 'CA', '37.78506', '-122.40479', 'USA');
INSERT INTO `hotel` VALUES ('2536795', 'San Francisco Hotel', 'Via Magellano 8', 'Dorgali', 'NU', '40.282947', '9.635117', 'ITA');
INSERT INTO `hotel` VALUES ('5901', 'The Kimpton Buchanan', '1800 Sutter St', 'San Francisco', 'CA', '37.786532', '-122.430119', 'USA');
INSERT INTO `hotel` VALUES ('881699', 'Americas Best Value Inn San Mateo/San Francisco', '140 N Bayshore Blvd', 'San Mateo', 'CA', '37.576184', '-122.319361', 'USA');
INSERT INTO `hotel` VALUES ('519729', 'Hilton Garden Inn San Francisco Airport/Burlingame', '765 Airport Blvd', 'Burlingame', 'CA', '37.58983', '-122.34903', 'USA');
INSERT INTO `hotel` VALUES ('8727731', 'Hostal San Francisco de Asis', 'Av. Luis Pasteur No. #102', 'Santo Domingo', 'Distrito Nacional', '18.466594', '-69.898652', 'DOM');
INSERT INTO `hotel` VALUES ('15178126', 'Parker Guest House San Francisco', '520 Church Street', 'San Francisco', 'CA', '37.762465', '-122.428457', 'USA');
INSERT INTO `hotel` VALUES ('2508778', 'San Francisco Park - Adults Only', 'Calle Chalana, 7', 'Tias', 'Lanzarote', '28.921153', '-13.649678', 'ESP');
INSERT INTO `hotel` VALUES ('564858', 'Orchard Hotel', '665 Bush St', 'San Francisco', 'CA', '37.790248', '-122.408469', 'USA');
INSERT INTO `hotel` VALUES ('10939713', 'Almond Suite - San Francisco', 'San Francisco 335', 'Santiago', 'Region Metropolitana', '-33.449098', '-70.647018', 'CHL');
INSERT INTO `hotel` VALUES ('4705', 'The Westin San Francisco Airport', '1 Old Bayshore Hwy', 'Millbrae', 'CA', '37.604235', '-122.375922', 'USA');
INSERT INTO `hotel` VALUES ('915510', 'Argonaut Hotel - a Noble House Hotel', '495 Jefferson St', 'San Francisco', 'CA', '37.80766', '-122.420306', 'USA');
INSERT INTO `hotel` VALUES ('883306', 'Super 8 San Francisco / Near the Marina', '2440 Lombard St', 'San Francisco', 'CA', '37.799201', '-122.44162', 'USA');
INSERT INTO `hotel` VALUES ('422927', 'Courtyard San Francisco Fisherman\'s Wharf', '580 Beach St', 'San Francisco', 'CA', '37.806926', '-122.418486', 'USA');
INSERT INTO `hotel` VALUES ('11523', 'Le Meridien San Francisco', '333 Battery St', 'San Francisco', 'CA', '37.794725', '-122.400265', 'USA');
INSERT INTO `hotel` VALUES ('2406', 'Hotel Adagio, Autograph Collection', '550 Geary St', 'San Francisco', 'CA', '37.786852', '-122.41252', 'USA');
INSERT INTO `hotel` VALUES ('20488', 'Marriott San Francisco Fisherman\'s Wharf', '1250 Columbus Ave', 'San Francisco', 'CA', '37.80561', '-122.41781', 'USA');
INSERT INTO `hotel` VALUES ('18962', 'Days Inn San Francisco At The Beach', '2600 Sloat Blvd', 'San Francisco', 'CA', '37.73553', '-122.50206', 'USA');
INSERT INTO `hotel` VALUES ('4044', 'San Mateo Marriott San Francisco Airport', '1770 S Amphlett Blvd', 'San Mateo', 'CA', '37.556767', '-122.300253', 'USA');
INSERT INTO `hotel` VALUES ('547988', 'HYATT house Emeryville/San Francisco Bay Area', '5800 Shellmound Street', 'Emeryville', 'CA', '37.839287', '-122.293408', 'USA');
INSERT INTO `hotel` VALUES ('4812964', 'Hotel Riu San Francisco - Adults Only', 'Carrer de Llaut, 24', 'Playa de Palma', 'Mallorca', '39.51709', '2.745557', 'ESP');
INSERT INTO `hotel` VALUES ('9022184', 'Posada San Francisco', '405 San Francisco ', 'San Juan', null, '18.466413', '-66.112209', 'PRI');
INSERT INTO `hotel` VALUES ('13950', 'Aloft San Francisco Airport', '401 E Millbrae Ave', 'Millbrae', 'CA', '37.60411', '-122.37784', 'USA');
INSERT INTO `hotel` VALUES ('11771', 'Hotel Diva', '440 Geary St', 'San Francisco', 'CA', '37.78716', '-122.41049', 'USA');
INSERT INTO `hotel` VALUES ('8638', 'Americas Best Value Inn-Corte Madera/San Francisco', '1595 Casa Buena Dr', 'Corte Madera', 'CA', '37.92319', '-122.51351', 'USA');
INSERT INTO `hotel` VALUES ('3941365', 'Hotel de San Francisco', 'Caseros 160', 'Salta', 'Salta', '-24.790172', '-65.40508', 'ARG');
INSERT INTO `hotel` VALUES ('10323', 'Hilton Garden Inn San Francisco/Oakland Bay Bridge', '1800 Powell Street', 'Emeryville', 'CA', '37.837773', '-122.298142', 'USA');
SET FOREIGN_KEY_CHECKS=1;
