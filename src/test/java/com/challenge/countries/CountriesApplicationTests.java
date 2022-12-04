package com.challenge.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CountriesApplicationTests {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;

	@Test
	void whenMain_thenContextLoads() {
		CountriesApplication.main(new String[] {});
	}

	@Test
	void whenConfigure_thenOk() {
		// GIVEN
		CountriesApplication servletInitializer = new CountriesApplication();
		when(springApplicationBuilder.sources(CountriesApplication.class)).thenReturn(springApplicationBuilder);

		// WHEN
		SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

		// THEN
		verify(springApplicationBuilder).sources(CountriesApplication.class);
		assertEquals(springApplicationBuilder,result);
	}

}
