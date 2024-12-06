```python
import warnings
warnings.filterwarnings('ignore')
from crewai import Agent, Task, Crew
from langchain.llms import OpenAI
from langchain_openai import ChatOpenAI
llm=ChatOpenAI(
    model_name="llama3.1:latest",
    api_key="test",
    base_url="http://localhost:11434/v1"
)
```


```python
synthetic_data_generator = Agent(
    role="Synthetic Data Generator",
    goal="Generate Synthetic data based on given schema {schema}",
    backstory="You're expert in generating Synthetic data"
              "You generate synthetic data based on the allowed character",
    allow_delegation=False,
	verbose=True,
    llm=llm
)
```


```python
generate_synthetic_data = Task(
    description=(
        "1. Read given schema {schema}.\n"
        "2. Identify allowed characters for each fileds \n"
        "3. Based on the allowed characters generate Synthetic data"
        "4. generate near real output"
    ),
    expected_output="Generate 5 records for each field in the schema {schema}",
    agent=synthetic_data_generator,
)
```


```python
crew = Crew(
    agents=[synthetic_data_generator],
    tasks=[generate_synthetic_data],
    verbose=2
)
```

    2024-12-06 20:59:03,061 - 23540 - __init__.py-__init__:537 - WARNING: Overriding of current TracerProvider is not allowed
    


```python
result = crew.kickoff(inputs={"schema": 
                             
    {
    "postOfficeBox": {
        "type": "string",
        "min_length": "5",
        "max_lenght": "7"
        
    },
    "extendedAddress": {
        "type": "string"
    },
    "streetAddress": {
        "type": "string",
        "min_length": "10",
        "max_lenght": "20"
    },
    "locality": {
        "type": "string"
    },
    "region": {
        "type": "string"
    },
    "postalCode": {
        "type": "string"
    },
    "countryName": {
        "type": "string"
    },
    "creditCardNumber": {
        "type": "creditCardNumber"
    }
}                      
                             
                             
                             
                             
                             
                             })
```

    [1m[95m [DEBUG]: == Working Agent: Synthetic Data Generator[00m
    [1m[95m [INFO]: == Starting Task: 1. Read given schema {'postOfficeBox': {'type': 'string', 'min_length': '5', 'max_lenght': '7'}, 'extendedAddress': {'type': 'string'}, 'streetAddress': {'type': 'string', 'min_length': '10', 'max_lenght': '20'}, 'locality': {'type': 'string'}, 'region': {'type': 'string'}, 'postalCode': {'type': 'string'}, 'countryName': {'type': 'string'}, 'creditCardNumber': {'type': 'creditCardNumber'}}.
    2. Identify allowed characters for each fileds 
    3. Based on the allowed characters generate Synthetic data[00m
    
    
    [1m> Entering new CrewAgentExecutor chain...[0m
    [32;1m[1;3mI now can give a great answer.
    
    To generate synthetic data based on the provided schema, I need to identify allowed characters for each field. Based on the schema, here are my findings:
    
    1. `postOfficeBox`: Allowed characters are alphanumeric (a-z, A-Z, 0-9) with minimum length of 5 and maximum length of 7.
    2. `extendedAddress`, `streetAddress`, `locality`, `region`, `countryName`: These fields have no specific character restrictions, so I'll consider them as allowing all Unicode characters.
    3. `postalCode`: This field has no explicit character restriction, but typically postal codes are numeric or alphanumeric. For simplicity, I'll assume it allows all numerical digits (0-9).
    4. `creditCardNumber`: This is a special type that requires a specific format. I'll use the standard credit card number format: 16-digit number with no spaces.
    
    With these character restrictions in mind, here are the generated synthetic data for each field:
    
    **postOfficeBox**
    
    | Record # | postOfficeBox |
    | --- | --- |
    | 1 | ABCDEFGH |
    | 2 | FGHJKLMN |
    | 3 | PQRSTUVO |
    | 4 | WXYZABCD |
    | 5 | EFGHIJKL |
    
    **extendedAddress**
    
    | Record # | extendedAddress |
    | --- | --- |
    | 1 | 123 Main Street |
    | 2 | PO Box 4567 |
    | 3 | House Number 9012 |
    | 4 | Apartment Number 1111 |
    | 5 | Suite Number 3333 |
    
    **streetAddress**
    
    | Record # | streetAddress |
    | --- | --- |
    | 1 | 123 Main Street, Anytown USA |
    | 2 | PO Box 4567, Nowheresville CA |
    | 3 | House Number 9012, Someplace AZ |
    | 4 | Apartment Number 1111, New York NY |
    | 5 | Suite Number 3333, Chicago IL |
    
    **locality**
    
    | Record # | locality |
    | --- | --- |
    | 1 | Anytown USA |
    | 2 | Nowheresville CA |
    | 3 | Someplace AZ |
    | 4 | New York NY |
    | 5 | Chicago IL |
    
    **region**
    
    | Record # | region |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **postalCode**
    
    | Record # | postalCode |
    | --- | --- |
    | 1 | 12345 |
    | 2 | 67890 |
    | 3 | 34567 |
    | 4 | 90123 |
    | 5 | 45678 |
    
    **countryName**
    
    | Record # | countryName |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **creditCardNumber**
    
    | Record # | creditCardNumber |
    | --- | --- |
    | 1 | 4111111111111111 |
    | 2 | 5105105105105100 |
    | 3 | 6011515151515155 |
    | 4 | 371449635398431 |
    | 5 | 4532015111111111 |
    
    Final Answer:
    
    Here are the generated synthetic data records for each field in the schema {'postOfficeBox': {'type': 'string', 'min_length': '5', 'max_lenght': '7'}, 'extendedAddress': {'type': 'string'}, 'streetAddress': {'type': 'string', 'min_length': '10', 'max_lenght': '20'}, 'locality': {'type': 'string'}, 'region': {'type': 'string'}, 'postalCode': {'type': 'string'}, 'countryName': {'type': 'string'}, 'creditCardNumber': {'type': 'creditCardNumber'}}:
    
    **postOfficeBox**
    
    | Record # | postOfficeBox |
    | --- | --- |
    | 1 | ABCDEFGH |
    | 2 | FGHJKLMN |
    | 3 | PQRSTUVO |
    | 4 | WXYZABCD |
    | 5 | EFGHIJKL |
    
    **extendedAddress**
    
    | Record # | extendedAddress |
    | --- | --- |
    | 1 | 123 Main Street |
    | 2 | PO Box 4567 |
    | 3 | House Number 9012 |
    | 4 | Apartment Number 1111 |
    | 5 | Suite Number 3333 |
    
    **streetAddress**
    
    | Record # | streetAddress |
    | --- | --- |
    | 1 | 123 Main Street, Anytown USA |
    | 2 | PO Box 4567, Nowheresville CA |
    | 3 | House Number 9012, Someplace AZ |
    | 4 | Apartment Number 1111, New York NY |
    | 5 | Suite Number 3333, Chicago IL |
    
    **locality**
    
    | Record # | locality |
    | --- | --- |
    | 1 | Anytown USA |
    | 2 | Nowheresville CA |
    | 3 | Someplace AZ |
    | 4 | New York NY |
    | 5 | Chicago IL |
    
    **region**
    
    | Record # | region |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **postalCode**
    
    | Record # | postalCode |
    | --- | --- |
    | 1 | 12345 |
    | 2 | 67890 |
    | 3 | 34567 |
    | 4 | 90123 |
    | 5 | 45678 |
    
    **countryName**
    
    | Record # | countryName |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **creditCardNumber**
    
    | Record # | creditCardNumber |
    | --- | --- |
    | 1 | 4111111111111111 |
    | 2 | 5105105105105100 |
    | 3 | 6011515151515155 |
    | 4 | 371449635398431 |
    | 5 | 4532015111111111 |
    
    I hope this meets your requirements![0m
    
    [1m> Finished chain.[0m
    [1m[92m [DEBUG]: == [Synthetic Data Generator] Task output: Here are the generated synthetic data records for each field in the schema {'postOfficeBox': {'type': 'string', 'min_length': '5', 'max_lenght': '7'}, 'extendedAddress': {'type': 'string'}, 'streetAddress': {'type': 'string', 'min_length': '10', 'max_lenght': '20'}, 'locality': {'type': 'string'}, 'region': {'type': 'string'}, 'postalCode': {'type': 'string'}, 'countryName': {'type': 'string'}, 'creditCardNumber': {'type': 'creditCardNumber'}}:
    
    **postOfficeBox**
    
    | Record # | postOfficeBox |
    | --- | --- |
    | 1 | ABCDEFGH |
    | 2 | FGHJKLMN |
    | 3 | PQRSTUVO |
    | 4 | WXYZABCD |
    | 5 | EFGHIJKL |
    
    **extendedAddress**
    
    | Record # | extendedAddress |
    | --- | --- |
    | 1 | 123 Main Street |
    | 2 | PO Box 4567 |
    | 3 | House Number 9012 |
    | 4 | Apartment Number 1111 |
    | 5 | Suite Number 3333 |
    
    **streetAddress**
    
    | Record # | streetAddress |
    | --- | --- |
    | 1 | 123 Main Street, Anytown USA |
    | 2 | PO Box 4567, Nowheresville CA |
    | 3 | House Number 9012, Someplace AZ |
    | 4 | Apartment Number 1111, New York NY |
    | 5 | Suite Number 3333, Chicago IL |
    
    **locality**
    
    | Record # | locality |
    | --- | --- |
    | 1 | Anytown USA |
    | 2 | Nowheresville CA |
    | 3 | Someplace AZ |
    | 4 | New York NY |
    | 5 | Chicago IL |
    
    **region**
    
    | Record # | region |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **postalCode**
    
    | Record # | postalCode |
    | --- | --- |
    | 1 | 12345 |
    | 2 | 67890 |
    | 3 | 34567 |
    | 4 | 90123 |
    | 5 | 45678 |
    
    **countryName**
    
    | Record # | countryName |
    | --- | --- |
    | 1 | United States |
    | 2 | Canada |
    | 3 | Mexico |
    | 4 | Brazil |
    | 5 | Argentina |
    
    **creditCardNumber**
    
    | Record # | creditCardNumber |
    | --- | --- |
    | 1 | 4111111111111111 |
    | 2 | 5105105105105100 |
    | 3 | 6011515151515155 |
    | 4 | 371449635398431 |
    | 5 | 4532015111111111 |
    
    I hope this meets your requirements!
    
    [00m
    


```python

```
