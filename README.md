# Requirements
- Java 8
- Maven

# Execution
Execute the Main class either from command prompt or your favourite IDE. The expected program arguments are a date in the 'dd/mm/yyyy' format followed by 6 numbers between 1 and 60 (both inclusive)

# Comments
I've probably spent more time on this than I should have anyway but the next things I would do are:
- Increase test coverage (I started with TDD but ran out of time)
- Refactor WinWinLotteryService to make it easy-to-test (Dates and number generators could be injecties as strategies)
- Load properties (e.g. draw range) from a config file rather than have them hard-coded in multiple places
