package net.nickg.charter.rewards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.nickg.charter.rewards.domain.PurchaseRecord;
import net.nickg.charter.rewards.repo.PurchaseRecordRepository;
import net.nickg.charter.rewards.service.PurchaseRecordService;
import net.nickg.charter.rewards.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RewardsApplication {
	private static final Logger log = LoggerFactory.getLogger(RewardsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RewardsApplication.class, args);
	}


	@Bean
	@Profile("!data")
	CommandLineRunner loadData(PurchaseRecordService purchaseRecordService, RewardsService rewardsService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			TypeReference<List<PurchaseRecord>> listTypeReference = new TypeReference<>() {
			};

			InputStream inputStream = TypeReference.class.getResourceAsStream("/purchases.json");
			List<PurchaseRecord> records = mapper.readValue(inputStream, listTypeReference);

			records.stream().forEach(System.out::println);
			purchaseRecordService.save(records);

			rewardsService.getRewardSummary();
		};
	}

	@Bean
	@Profile("data")
	CommandLineRunner generateData(PurchaseRecordService purchaseRecordService) throws IOException {
		return args -> {
			List<PurchaseRecord> records = new ArrayList<>();
			fillWithRandomPurchaseRecords(records);

			var savedRecords = purchaseRecordService.save(records);

			// Create .json file with purchase records
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			mapper.writeValue(new File("purchases.json"), records);

			savedRecords.forEach(System.out::println);
		};


	}

	private static void fillWithRandomPurchaseRecords(List<PurchaseRecord> records) {
		final var rand = new SecureRandom();

		final var numberOfRecordsToGenerate = 100;
		final var maxAmountExclusive = 201;
		final var julyInclusive = 7;
		final var octoberExclusive = 10;
		final var year = 2022;
		final var monthDayMin = 1;
		final var monthDayMax = 30;


		// Purchases with amounts between 0 and 200 are most relevant.
		// Amounts are generated using integers, even though PurchaseRecords uses BigDecimal, to put a limit on how
		//  deep to model this sample domain.

		for (int i = 0; i < numberOfRecordsToGenerate; i++) {
			var amount = rand.nextInt(maxAmountExclusive);
			var month = rand.nextInt(julyInclusive, octoberExclusive);
			// Use days of month 1 to 30 for simplicity here.
			// Would use Month.of(month).length(isLeapYear(year)) or similar date utils for real application
			var day = rand.nextInt(monthDayMin, monthDayMax);
			var customerId = rand.nextLong(1, 10);

			PurchaseRecord p = new PurchaseRecord(
										new BigDecimal(amount),
										LocalDate.of(year, month, day),
										customerId );
			records.add(p);
		}
	}
}
