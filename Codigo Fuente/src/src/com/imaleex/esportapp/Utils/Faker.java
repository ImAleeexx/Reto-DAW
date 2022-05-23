package com.imaleex.esportapp.Utils;

import java.util.Random;

public class Faker {

    public static final String[] firstNames = {"Adán", "Agustín", "Alberto", "Alejandro", "Alfonso", "Alfredo", "Andrés", "Antonio", "Armando", "Arturo", "Benito", "Benjamín", "Bernardo", "Carlos", "César", "Claudio", "Clemente", "Cristián", "Cristóbal", "Daniel", "David", "Diego", "Eduardo", "Emilio", "Enrique", "Ernesto", "Esteban", "Federico", "Felipe", "Fernando", "Francisco", "Gabriel", "Gerardo", "Germán", "Gilberto", "Gonzalo", "Gregorio", "Guillermo", "Gustavo", "Hernán", "Homero", "Horacio", "Hugo", "Ignacio", "Jacobo", "Jaime", "Javier", "Jerónimo", "Jesús", "Joaquín", "Jorge", "Jorge Luis", "José", "José Eduardo", "José Emilio", "José Luis", "José María", "Juan", "Juan Carlos", "Julio", "Julio César", "Lorenzo", "Lucas", "Luis", "Luis Miguel", "Manuel", "Marco Antonio", "Marcos", "Mariano", "Mario", "Martín", "Mateo", "Miguel", "Miguel Ángel", "Nicolás", "Octavio", "Óscar", "Pablo", "Patricio", "Pedro", "Rafael", "Ramiro", "Ramón", "Raúl", "Ricardo", "Roberto", "Rodrigo", "Rubén", "Salvador", "Samuel", "Sancho", "Santiago", "Sergio", "Teodoro", "Timoteo", "Tomás", "Vicente", "Víctor", "Adela", "Adriana", "Alejandra", "Alicia", "Amalia", "Ana", "Ana Luisa", "Ana María", "Andrea", "Anita", "Ángela", "Antonia", "Ariadna", "Barbara", "Beatriz", "Berta", "Blanca", "Caridad", "Carla", "Carlota", "Carmen", "Carolina", "Catalina", "Cecilia", "Clara", "Claudia", "Concepción", "Conchita", "Cristina", "Daniela", "Débora", "Diana", "Dolores", "Lola", "Dorotea", "Elena", "Elisa", "Eloisa", "Elsa", "Elvira", "Emilia", "Esperanza", "Estela", "Ester", "Eva", "Florencia", "Francisca", "Gabriela", "Gloria", "Graciela", "Guadalupe", "Guillermina", "Inés", "Irene", "Isabel", "Isabela", "Josefina", "Juana", "Julia", "Laura", "Leonor", "Leticia", "Lilia", "Lorena", "Lourdes", "Lucía", "Luisa", "Luz", "Magdalena", "Manuela", "Marcela", "Margarita", "María", "María del Carmen", "María Cristina", "María Elena", "María Eugenia", "María José", "María Luisa", "María Soledad", "María Teresa", "Mariana", "Maricarmen", "Marilú", "Marisol", "Marta", "Mayte", "Mercedes", "Micaela", "Mónica", "Natalia", "Norma", "Olivia", "Patricia", "Pilar", "Ramona", "Raquel", "Rebeca", "Reina", "Rocío", "Rosa", "Rosalia", "Rosario", "Sara", "Silvia", "Sofía", "Soledad", "Sonia", "Susana", "Teresa", "Verónica", "Victoria", "Virginia", "Yolanda"};
    public static final String[] cityNames = {"Parla", "Telde", "Baracaldo", "San Fernando", "Torrevieja", "Lugo", "Santiago de Compostela", "Gerona", "Cáceres", "Lorca", "Coslada", "Talavera de la Reina", "El Puerto de Santa María", "Cornellá de Llobregat", "Avilés", "Palencia", "Gecho", "Orihuela", "Pontevedra", "Pozuelo de Alarcón", "Toledo", "El Ejido", "Guadalajara", "Gandía", "Ceuta", "Ferrol", "Chiclana de la Frontera", "Manresa", "Roquetas de Mar", "Ciudad Real", "Rubí", "Benidorm", "San Sebastían de los Reyes", "Ponferrada", "Zamora", "Alcalá de Guadaira", "Fuengirola", "Mijas", "Sanlúcar de Barrameda", "La Línea de la Concepción", "Majadahonda", "Sagunto", "El Prat de LLobregat", "Viladecans", "Linares", "Alcoy", "Irún", "Estepona", "Torremolinos", "Rivas-Vaciamadrid", "Molina de Segura", "Paterna", "Granollers", "Santa Lucía de Tirajana", "Motril", "Cerdañola del Vallés", "Arrecife", "Segovia", "Torrelavega", "Elda", "Mérida", "Ávila", "Valdemoro", "Cuenta", "Collado Villalba", "Benalmádena", "Mollet del Vallés", "Puertollano", "Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma de Mallorca", "Las Palmas de Gran Canaria", "Bilbao", "Córdoba", "Alicante", "Valladolid", "Vigo", "Gijón", "Hospitalet de LLobregat", "La Coruña", "Granada", "Vitoria", "Elche", "Santa Cruz de Tenerife", "Oviedo", "Badalona", "Cartagena", "Móstoles", "Jerez de la Frontera", "Tarrasa", "Sabadell", "Alcalá de Henares", "Pamplona", "Fuenlabrada", "Almería", "San Sebastián", "Leganés", "Santander", "Burgos", "Castellón de la Plana", "Alcorcón", "Albacete", "Getafe", "Salamanca", "Huelva", "Logroño", "Badajoz", "San Cristróbal de la Laguna", "León", "Tarragona", "Cádiz", "Lérida", "Marbella", "Mataró", "Dos Hermanas", "Santa Coloma de Gramanet", "Jaén", "Algeciras", "Torrejón de Ardoz", "Orense", "Alcobendas", "Reus", "Calahorra", "Inca"};
    public static final String[] provinceNames = {"Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz", "Barcelona", "Burgos", "Cantabria", "Castellón", "Ciudad Real", "Cuenca", "Cáceres", "Cádiz", "Córdoba", "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña", "La Rioja", "Las Palmas", "León", "Lugo", "lérida", "Madrid", "Murcia", "Málaga", "Navarra", "Orense", "Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"};
    public static final String[] lastNames = {"Abeyta", "Abrego", "Abreu", "Acevedo", "Acosta", "Acuña", "Adame", "Adorno", "Agosto", "Aguayo", "Aguilar", "Aguilera", "Aguirre", "Alanis", "Alaníz", "Alarcón", "Alba", "Alcalá", "Alcántar", "Alcaraz", "Alejandro", "Alemán", "Alfaro", "Alicea", "Almanza", "Almaráz", "Almonte", "Alonso", "Alonzo", "Altamirano", "Alva", "Alvarado", "Álvarez", "Amador", "Amaya", "Anaya", "Anguiano", "Angulo", "Aparicio", "Apodaca", "Aponte", "Aragón", "Araña", "Aranda", "Arce", "Archuleta", "Arellano", "Arenas", "Arevalo", "Argüello", "Arias", "Armas", "Armendáriz", "Armenta", "Armijo", "Arredondo", "Arreola", "Arriaga", "Arroyo", "Arteaga", "Atencio", "Ávalos", "Ávila", "Avilés", "Ayala", "Baca", "Badillo", "Báez", "Baeza", "Bahena", "Balderas", "Ballesteros", "Banda", "Bañuelos", "Barajas", "Barela", "Barragán", "Barraza", "Barrera", "Barreto", "Barrientos", "Barrios", "Batista", "Becerra", "Beltrán", "Benavides", "Benavídez", "Benítez", "Bermúdez", "Bernal", "Berríos", "Betancourt", "Blanco", "Bonilla", "Borrego", "Botello", "Bravo", "Briones", "Briseño", "Brito", "Bueno", "Burgos", "Bustamante", "Bustos", "Caballero", "Cabán", "Cabrera", "Cadena", "Caldera", "Calderón", "Calvillo", "Camacho", "Camarillo", "Campos", "Canales", "Candelaria", "Cano", "Cantú", "Caraballo", "Carbajal", "Cárdenas", "Cardona", "Carmona", "Carranza", "Carrasco", "Carrasquillo", "Carreón", "Carrera", "Carrero", "Carrillo", "Carrión", "Carvajal", "Casanova", "Casares", "Casarez", "Casas", "Casillas", "Castañeda", "Castellanos", "Castillo", "Castro", "Cavazos", "Cazares", "Ceballos", "Cedillo", "Ceja", "Centeno", "Cepeda", "Cerda", "Cervantes", "Cervántez", "Chacón", "Chapa", "Chavarría", "Chávez", "Cintrón", "Cisneros", "Collado", "Collazo", "Colón", "Colunga", "Concepción", "Contreras", "Cordero", "Córdova", "Cornejo", "Corona", "Coronado", "Corral", "Corrales", "Correa", "Cortés", "Cortéz", "Cotto", "Covarrubias", "Crespo", "Cruz", "Cuellar", "Curiel", "Dávila", "de Anda", "de Jesús", "Delacrúz", "Delafuente", "Delagarza", "Delao", "Delapaz", "Delarosa", "Delatorre", "Deleón", "Delgadillo", "Delgado", "Delrío", "Delvalle", "Díaz", "Domínguez", "Duarte", "Dueñas", "Durán", "Echevarría", "Elizondo", "Enríquez", "Escalante", "Escamilla", "Escobar", "Escobedo", "Esparza", "Espinal", "Espino", "Espinosa", "Espinoza", "Esquibel", "Esquivel", "Estévez", "Estrada", "Fajardo", "Farías", "Feliciano", "Fernández", "Ferrer", "Fierro", "Figueroa", "Flores", "Flórez", "Fonseca", "Frías", "Fuentes", "Gaitán", "Galarza", "Galindo", "Gallardo", "Gallegos", "Galván", "Gálvez", "Gamboa", "Gámez", "Gaona", "Garay", "García", "Garibay", "Garica", "Garrido", "Garza", "Gastélum", "Gaytán", "Gil", "Girón", "Godínez", "Godoy", "Gómez", "González", "Gracia", "Granado", "Granados", "Griego", "Grijalva", "Guajardo", "Guardado", "Guerra", "Guerrero", "Guevara", "Guillén", "Gurule", "Gutiérrez", "Guzmán", "Haro", "Henríquez", "Heredia", "Hernández", "Herrera", "Hidalgo", "Hinojosa", "Holguín", "Huerta", "Hurtado", "Ibarra", "Iglesias", "Irizarry", "Jaime", "Jaimes", "Jáquez", "Jaramillo", "Jasso", "Jiménez", "Juárez", "Jurado", "Laboy", "Lara", "Laureano", "Leal", "Lebrón", "Ledesma", "Leiva", "Lemus", "León", "Lerma", "Leyva", "Limón", "Linares", "Lira", "Llamas", "Loera", "Lomeli", "Longoria", "López", "Lovato", "Loya", "Lozada", "Lozano", "Lucero", "Lucio", "Luevano", "Lugo", "Luna", "Macías", "Madera", "Madrid", "Madrigal", "Maestas", "Magaña", "Malave", "Maldonado", "Manzanares", "Mares", "Marín", "Márquez", "Marrero", "Marroquín", "Martínez", "Mascareñas", "Mata", "Mateo", "Matías", "Matos", "Maya", "Mayorga", "Medina", "Medrano", "Mejía", "Meléndez", "Melgar", "Mena", "Menchaca", "Méndez", "Mendoza", "Menéndez", "Meraz", "Mercado", "Merino", "Mesa", "Meza", "Miramontes", "Miranda", "Mireles", "Mojica", "Molina", "Mondragón", "Monroy", "Montalvo", "Montañez", "Montaño", "Montemayor", "Montenegro", "Montero", "Montes", "Montéz", "Montoya", "Mora", "Morales", "Moreno", "Mota", "Moya", "Munguía", "Muñiz", "Muñoz", "Murillo", "Muro", "Nájera", "Naranjo", "Narváez", "Nava", "Navarrete", "Navarro", "Nazario", "Negrete", "Negrón", "Nevárez", "Nieto", "Nieves", "Niño", "Noriega", "Núñez", "Ocampo", "Ocasio", "Ochoa", "Ojeda", "Olivares", "Olivárez", "Olivas", "Olivera", "Olivo", "Olmos", "Olvera", "Ontiveros", "Oquendo", "Ordóñez", "Orellana", "Ornelas", "Orosco", "Orozco", "Orta", "Ortega", "Ortiz", "Osorio", "Otero", "Ozuna", "Pabón", "Pacheco", "Padilla", "Padrón", "Páez", "Pagan", "Palacios", "Palomino", "Palomo", "Pantoja", "Paredes", "Parra", "Partida", "Patiño", "Paz", "Pedraza", "Pedroza", "Pelayo", "Peña", "Perales", "Peralta", "Perea", "Pérez", "Pichardo", "Piña", "Pineda", "Pizarro", "Polanco", "Ponce", "Porras", "Portillo", "Posada", "Prado", "Preciado", "Prieto", "Puente", "Puga", "Pulido", "Quesada", "Quezada", "Quiñones", "Quiñónez", "Quintana", "Quintanilla", "Quintero", "Quiróz", "Rael", "Ramírez", "Ramos", "Rangel", "Rascón", "Raya", "Razo", "Regalado", "Rendón", "Rentería", "Reséndez", "Reyes", "Reyna", "Reynoso", "Rico", "Rincón", "Riojas", "Ríos", "Rivas", "Rivera", "Rivero", "Robledo", "Robles", "Rocha", "Rodarte", "Rodríguez", "Rojas", "Rojo", "Roldán", "Rolón", "Romero", "Romo", "Roque", "Rosado", "Rosales", "Rosario", "Rosas", "Roybal", "Rubio", "Ruelas", "Ruíz", "Saavedra", "Sáenz", "Saiz", "Salas", "Salazar", "Salcedo", "Salcido", "Saldaña", "Saldivar", "Salgado", "Salinas", "Samaniego", "Sanabria", "Sánchez", "Sandoval", "Santacruz", "Santana", "Santiago", "Santillán", "Sarabia", "Sauceda", "Saucedo", "Sedillo", "Segovia", "Segura", "Sepúlveda", "Serna", "Serrano", "Serrato", "Sevilla", "Sierra", "Sisneros", "Solano", "Solís", "Soliz", "Solorio", "Solorzano", "Soria", "Sosa", "Sotelo", "Soto", "Suárez", "Tafoya", "Tamayo", "Tamez", "Tapia", "Tejada", "Tejeda", "Téllez", "Tello", "Terán", "Terrazas", "Tijerina", "Tirado", "Toledo", "Toro", "Torres", "Tórrez", "Tovar", "Trejo", "Treviño", "Trujillo", "Ulibarri", "Ulloa", "Urbina", "Ureña", "Urías", "Uribe", "Urrutia", "Vaca", "Valadez", "Valdés", "Valdez", "Valdivia", "Valencia", "Valentín", "Valenzuela", "Valladares", "Valle", "Vallejo", "Valles", "Valverde", "Vanegas", "Varela", "Vargas", "Vázquez", "Vega", "Vela", "Velasco", "Velásquez", "Velázquez", "Vélez", "Véliz", "Venegas", "Vera", "Verdugo", "Verduzco", "Vergara", "Viera", "Vigil", "Villa", "Villagómez", "Villalobos", "Villalpando", "Villanueva", "Villarreal", "Villaseñor", "Villegas", "Yáñez", "Ybarra", "Zambrano", "Zamora", "Zamudio", "Zapata", "Zaragoza", "Zarate", "Zavala", "Zayas", "Zelaya", "Zepeda", "Zúñiga"};
    public static final String[] streetSufixes = {"Aldea", "Apartamento", "Arrabal", "Arroyo", "Avenida", "Bajada", "Barranco", "Barrio", "Bloque", "Calle", "Calleja", "Camino", "Carretera", "Caserio", "Colegio", "Colonia", "Conjunto", "Cuesta", "Chalet", "Edificio", "Entrada", "Escalinata", "Explanada", "Extramuros", "Extrarradio", "Ferrocarril", "Glorieta", "Gran Subida", "Grupo", "Huerta", "Jardines", "Lado", "Lugar", "Manzana", "Masía", "Mercado", "Monte", "Muelle", "Municipio", "Parcela", "Parque", "Partida", "Pasaje", "Paseo", "Plaza", "Poblado", "Polígono", "Prolongación", "Puente", "Puerta", "Quinta", "Ramal", "Rambla", "Rampa", "Riera", "Rincón", "Ronda", "Rua", "Salida", "Sector", "Sección", "Senda", "Solar", "Subida", "Terrenos", "Torrente", "Travesía", "Urbanización", "Vía", "Vía Pública"};
    public static final String[] productNames = {"Silla", "Coche", "Ordenador", "Teclado", "Raton", "Bicicleta", "Pelota", "Guantes", "Pantalones", "Camiseta", "Mesa", "Zapatos", "Gorro", "Toallas", "Sopa", "Atún", "Pollo", "Pescado", "Queso", "Bacon", "Pizza", "Ensalada", "Salchichas", "Patatas fritas"};
    public static final String[] businessPrefixes = {"", "Hermanos", "Informatica", "SAT", "Almacenes", "Maquinarias", "Componentes", "Logistica", "Materiales", "Electrodomesticos", "Hijos de", "Muebles", "Embutidos", "Calzados", "Congelados", "Alimentaciones"};
    public static final String[] businesSufixes = {"S.L.", "", "S.A.U.", "S.A.", "S. COOP"};

    public static String firstName() {
        return firstNames[randomPosNumber(firstNames.length - 1)];
    }

    public static String lastName() {
        return lastNames[randomPosNumber(lastNames.length - 1)];
    }

    public static String fullName() {
        return firstName() + " " + lastName() + " " + lastName();
    }

    public static String city() {
        return cityNames[randomPosNumber(cityNames.length - 1)];
    }

    public static String streetSufix() {
        return streetSufixes[randomPosNumber(streetSufixes.length - 1)];
    }

    public static String provinceName() {
        return provinceNames[randomPosNumber(provinceNames.length - 1)];
    }

    public static String postalCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(randomPosNumber(10));
        }
        return sb.toString();
    }

    public static String streetName() {
        return streetSufix() + " " + lastName();
    }

    public static String composedStreetName() {
        return streetSufix() + " " + firstName() + " " + lastName();
    }

    public static String fullAddress() {
        return streetName() + ", " + city() + ", " + postalCode() + ", " + provinceName();
    }

    public static String getPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(6);
        for (int i = 0; i < 8; i++) {
            sb.append(randomPosNumber(10));
        }
        return sb.toString();
    }

    public static String businessSufix() {
        return businesSufixes[randomPosNumber(businesSufixes.length - 1)];
    }

    public static String businessPrefix() {
        return businessPrefixes[randomPosNumber(businessPrefixes.length - 1)];
    }

    public static String fullBusinessName() {
        String businessName = businessPrefix() + " " + lastName() + " " + businessSufix();
        if (Character.isWhitespace(businessName.charAt(0))) {
            businessName = businessName.substring(0, 0) + businessName.substring(1);
        }
        return businessName;
    }

    public static String bussinessName() {
        return lastName() + " " + businessSufix();
    }

    public static String getDni() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(randomPosNumber(10));
        }
        char[] dniChars = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int moduleDni = Integer.parseInt(sb.toString()) % 23;
        sb.append(dniChars[moduleDni]);
        return sb.toString();
    }


    public static String getSegSocial() {
        StringBuilder sb = new StringBuilder();
        int codProv = randomPosNumber(53) + 1;
        if (codProv < 10) {
            sb.append(0);
        }
        sb.append(codProv);
        for (int i = 0; i < 8; i++) {
            sb.append(randomPosNumber(10));
        }
        long moduleLongSs = Long.parseLong(sb.toString());
        int moduleSs = (int) (moduleLongSs % 97);
        if (moduleSs < 10) {
            sb.append(0);
        }
        sb.append(moduleSs);
        return sb.toString();
    }


    public static String getProductName() {
        return productNames[randomPosNumber(productNames.length - 1)];
    }

    public static int randomPosNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }
}

