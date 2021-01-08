package com.stock_management;

import com.stock_management.entity.Customer;
import com.stock_management.entity.Role;
import com.stock_management.entity.UserProfile;
import com.stock_management.repository.CustomerRepository;
import com.stock_management.service.RoleService;
import com.stock_management.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StockControlSystemApplication extends SpringBootServletInitializer {

	private final CustomerRepository customerRepository;
	private final RoleService roleService;
	private final UserService userService;

	public StockControlSystemApplication(CustomerRepository customerRepository, RoleService roleService, UserService userService) {
		this.customerRepository = customerRepository;
		this.roleService = roleService;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockControlSystemApplication.class, args);
	}

	@PostConstruct
	public void initPost() {
		Customer customer = new Customer(1L, "anonymous", "anonymous", null, null);
		if (!customerRepository.existsByFirstNameAndLastName(customer.getFirstName(), customer.getLastName())) {
			customerRepository.save(customer);
		}

		List<Role> roles = Stream.of(
				new Role(1L, "ADMIN"),
				new Role(2L, "CASHIER")
		).collect(Collectors.toList());
		roleService.saveRoles(roles);

		Set<Role> roleForAdmin = Stream.of(
				new Role(1L, "ADMIN")
		).collect(Collectors.toSet());

		Set<Role> roleForCashier = Stream.of(
				new Role(2L, "CASHIER")
		).collect(Collectors.toSet());

		List<UserProfile> userProfiles = Stream.of(
				new UserProfile(1L, "m.awad.l", "aw@d.l2923", "Awad", "Luckhoo", 24, "test@test.com", 9999999, roleForAdmin),
				new UserProfile(2L, "nazeera.e", "naZ1r@h4238", "Nazeera", "Elahee", 40, "test@test.com", 9999999, roleForCashier),
				new UserProfile(3L, "premila.b", "pRem1L@4812", "Premila", "Beebakly", 40, "test@test.com", 9999999, roleForCashier),
				new UserProfile(4L, "iqbaal.b", "iqB@@19821", "Iqbaal", "Bhugalee", 40, "test@test.com", 9999999, roleForCashier),
				new UserProfile(5L, "nazo.l", "N@z08941", "Nazamoodden", "Luckhoo", 60, "test@test.com", 9999999, roleForAdmin),
				new UserProfile(6L, "b.hanaa.l", "h@N@@8192", "Hanaa", "Luckhoo", 27, "test@test.com", 9999999, roleForAdmin),
				new UserProfile(6L, "m.ayash.l", "ay@$h9120", "Ayash", "Luckhoo", 23, "test@test.com", 9999999, roleForAdmin)
		).collect(Collectors.toList());
		userService.saveUsers(userProfiles);
	}
}
