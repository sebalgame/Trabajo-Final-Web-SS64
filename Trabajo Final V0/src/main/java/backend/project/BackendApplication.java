package backend.project;

import backend.project.entities.*;
import backend.project.repositories.*;
import backend.project.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(

			PromoterRepository promoterRepository,
			UserRepository userRepository,
			AuthorityRepository authorityRepository,
			ClientRepository clientRepository,
			EventRepository eventRepository,
			EventTypeRepository eventTypeRepository,
			FavoriteRepository favoriteRepository,
			TransactionRepository transactionRepository,
			PurchasedTicketsRepository purchasedTicketsRepository,
			CityRepository cityRepository,
			TicketTypeRepository ticketTypeRepository
		) {
		return args -> {

			// Crear Authority
			Authority authorityConsulta = new Authority(0L, "CONSULTA",null);
			Authority authorityRegistro = new Authority(0L, "REGISTRO",null);
			Authority authorityBORRAR = new Authority(0L, "BORRAR",null);
			Authority authorityBORRAR_FORZADO = new Authority(0L, "BORRAR_FORZADO",null);
			Authority authorityCONSULTA_ESTADO = new Authority(0L, "CONSULTA_ESTADO",null);
			authorityConsulta = authorityRepository.save(authorityConsulta);
			authorityRegistro = authorityRepository.save(authorityRegistro);
			authorityBORRAR = authorityRepository.save(authorityBORRAR);
			authorityBORRAR_FORZADO = authorityRepository.save(authorityBORRAR_FORZADO);
			authorityCONSULTA_ESTADO = authorityRepository.save(authorityCONSULTA_ESTADO);

			//  User
			
			User user1 = new User(0L, "johndoe", "password123", true, authorityConsulta);
			User user2 = new User(0L, "janesmith", "password456", true, authorityRegistro);
			user1.setPassword( new BCryptPasswordEncoder().encode(user1.getPassword()));
			user2.setPassword( new BCryptPasswordEncoder().encode(user2.getPassword()));
			user1 = userRepository.save(user1);
			user2 = userRepository.save(user2);

			//  Clientes
			Client client1 = new Client(0L, "John", "Doe", "M", 30, "123456789", "987654321", user1);
			Client client2 = new Client(0L, "Jane", "Smith", "F", 28, "987654321", "123456789", user2);
			client1 = clientRepository.save(client1);
			client2 = clientRepository.save(client2);

			//  Ciudad
			City city1 = new City(0L, "New York");
			City city2 = new City(0L, "Los Angeles");
			city1 = cityRepository.save(city1);
			city2 = cityRepository.save(city2);

			//  Tipo de Evento
			EventType eventType1 = new EventType(0L, "Music", "A live music event", "Live Concert");
			EventType eventType2 = new EventType(0L, "Conference", "Tech conference", "Tech Summit");

			eventType1 = eventTypeRepository.save(eventType1);
			eventType2 = eventTypeRepository.save(eventType2);

			//  Promotor
			Promoter promoter1 = new Promoter(0L, "Promotor 1");
			Promoter promoter2 = new Promoter(0L, "Promotor 2");
			promoter1 = promoterRepository.save(promoter1);
			promoter2 = promoterRepository.save(promoter2);

			//  Evento
			Event event1 = new Event(0L, 1000, LocalDateTime.now(), LocalDateTime.now().plusDays(1), "Central Park", "AC123", "image1.jpg", city1, eventType1, promoter1);
			Event event2 = new Event(0L, 500, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3), "Staples Center", "BC456", "image2.jpg", city2, eventType2, promoter2);
			event1 = eventRepository.save(event1);
			event2 = eventRepository.save(event2);

			//  Favoritos
			Favorite favorite1 = new Favorite(0L, client1, event1);
			Favorite favorite2 = new Favorite(0L, client2, event2);
			favorite1 = favoriteRepository.save(favorite1);
			favorite2 = favoriteRepository.save(favorite2);

			//  Transacciones
			Transaction transaction1 = new Transaction(0L, LocalDate.now(), 200.50, 2, client1);
			Transaction transaction2 = new Transaction(0L, LocalDate.now().minusDays(1), 150.75, 1, client2);
			transaction1 = transactionRepository.save(transaction1);
			transaction2 = transactionRepository.save(transaction2);

			//  Tipo de Entrada
			TicketType ticketType1 = new TicketType(0L, "VIP", 500.00, 100, event1);
			TicketType ticketType2 = new TicketType(0L, "General", 100.00, 500, event2);
			ticketType1 = ticketTypeRepository.save(ticketType1);
			ticketType2 = ticketTypeRepository.save(ticketType2);

			//  Entradas Compradas
			PurchasedTickets purchasedTickets1 = new PurchasedTickets(0L, 500.00, LocalDateTime.now(), ticketType1, transaction1);
			PurchasedTickets purchasedTickets2 = new PurchasedTickets(0L, 100.00, LocalDateTime.now().plusHours(1), ticketType2, transaction2);
			purchasedTickets1 = purchasedTicketsRepository.save(purchasedTickets1);
			purchasedTickets2 = purchasedTicketsRepository.save(purchasedTickets2);

			// Imprimir detalles en consola
			List<Client> clients = clientRepository.findAll();
			clients.forEach(client -> {
				System.out.println("Cliente: " + client.getFirstName() + " " + client.getLastName());
			});

			List<Event> events = eventRepository.findAll();
			events.forEach(event -> {
				System.out.println("Evento en: " + event.getAddress() + ", Capacidad: " + event.getCapacity());
			});

			List<Transaction> transactions = transactionRepository.findAll();
			transactions.forEach(transaction -> {
				System.out.println("Transacción para cliente: " + transaction.getClient().getFirstName() + ", Monto: " + transaction.getAmount());
			});
		};
	}
}

