#!/usr/bin/python
import time
from datetime import date

today = str(date.today())

idToUse = str(input("Enter your ID you will test with: "))

file = open('testdata.json', 'w')

data = """{
  \"requests\" : {
    \"-L0IDHzf1HA_xOM1PcAF\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 2,
        \"partyName\" : \"Karen\",
        \"payment\" : 5,
        \"restaurant\" : {
          \"restaurantAddress\" : \"3229 Helms Ave, Los Angeles, CA 90034, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJraWfUYK5woARS-2eei5FyqA\",
          \"restaurantName\" : \"Father's Office\",
          \"restaurantPhoneNumber\" : \"+1 310-736-2224\"
        },
        \"startTime\" : \"20:10\"
      },
      \"requestID\" : \"-L0IDHzf1HA_xOM1PcAF\",
      \"requestState\" : \"Pending\",
      \"requesterID\" : \"1758223867534643\",
      \"reserverID\" : \"\"
    },
    \"-L0IDv6tdWWnxz4VlQTS\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 3,
        \"partyName\" : \"Noodle Squad\",
        \"payment\" : 3,
        \"restaurant\" : {
          \"restaurantAddress\" : \"2057 Sawtelle Blvd, Los Angeles, CA 90025, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJ8zcv33S7woARjZOFCsp7E4s\",
          \"restaurantName\" : \"Tsujita LA Artisan Noodle\",
          \"restaurantPhoneNumber\" : \"+1 310-231-7373\"
        },
        \"startTime\" : \"21:12\"
      },
      \"requestID\" : \"-L0IDv6tdWWnxz4VlQTS\",
      \"requestState\" : \"Claimed\",
      \"requesterID\" : \"1758223867534643\",
      \"reserverID\" : \"""" + idToUse + """\"
    },
    \"-L0IE6FwwmjW38KCK_16\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 1,
        \"partyName\" : \"Burgerz\",
        \"payment\" : 1,
        \"restaurant\" : {
          \"restaurantAddress\" : \"Los Angeles, CA 90036, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJH44C7C-5woARTqDnfcetcBE\",
          \"restaurantName\" : \"Umami Burger Grove\",
          \"restaurantPhoneNumber\" : \"+1 323-954-8626\"
        },
        \"startTime\" : \"19:15\"
      },
      \"requestID\" : \"-L0IE6FwwmjW38KCK_16\",
      \"requestState\" : \"Completed\",
      \"requesterID\" : \"1758223867534643\",
      \"reserverID\" : \"""" + idToUse + """\"
    },
    \"-L0IERT-NbMrSoomPlIM\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 2,
        \"partyName\" : \"Tea Party\",
        \"payment\" : 3,
        \"restaurant\" : {
          \"restaurantAddress\" : \"8036 W 3rd St, Los Angeles, CA 90048, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJidjFGtu7woARfohy19sQ6LA\",
          \"restaurantName\" : \"Matcha Box\",
          \"restaurantPhoneNumber\" : \"+1 310-845-9392\"
        },
        \"startTime\" : \"15:16\"
      },
      \"requestID\" : \"-L0IERT-NbMrSoomPlIM\",
      \"requestState\" : \"Paid\",
      \"requesterID\" : \"1758223867534643\",
      \"reserverID\" : \"""" + idToUse + """\"
    },
    \"-L0IEvk9OkIdxG5fPhb5\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 3,
        \"partyName\" : \"Matt\",
        \"payment\" : 3,
        \"restaurant\" : {
          \"restaurantAddress\" : \"900 Westwood Blvd, Los Angeles, CA 90024, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJEedYU4G8woARJxntC4kRrsQ\",
          \"restaurantName\" : \"Chick-fil-A\",
          \"restaurantPhoneNumber\" : \"+1 310-443-8900\"
        },
        \"startTime\" : \"20:18\"
      },
      \"requestID\" : \"-L0IEvk9OkIdxG5fPhb5\",
      \"requestState\" : \"Pending\",
      \"requesterID\" : \"""" + idToUse + """\",
      \"reserverID\" : \"\"
    },
    \"-L0IF6pVjmrO-akojPaC\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 4,
        \"partyName\" : \"Susan\",
        \"payment\" : 2,
        \"restaurant\" : {
          \"restaurantAddress\" : \"972 Gayley Ave, Los Angeles, CA 90024, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJV-Tdg4O8woART_a5VDPk5mA\",
          \"restaurantName\" : \"Fat Sal's Deli\",
          \"restaurantPhoneNumber\" : \"+1 855-682-4373\"
        },
        \"startTime\" : \"20:19\"
      },
      \"requestID\" : \"-L0IF6pVjmrO-akojPaC\",
      \"requestState\" : \"Claimed\",
      \"requesterID\" : \"""" + idToUse + """\",
      \"reserverID\" : \"1758223867534643\"
    },
    \"-L0IFUj508xfEE4Yu9IU\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 2,
        \"partyName\" : \"Stanley\",
        \"payment\" : 3,
        \"restaurant\" : {
          \"restaurantAddress\" : \"920 Broxton Ave, Los Angeles, CA 90024, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJxWp69YO8woARm5vvUaC5udM\",
          \"restaurantName\" : \"Pieology Pizzeria\",
          \"restaurantPhoneNumber\" : \"+1 310-208-0901\"
        },
        \"startTime\" : \"20:21\"
      },
      \"requestID\" : \"-L0IFUj508xfEE4Yu9IU\",
      \"requestState\" : \"Claimed\",
      \"requesterID\" : \"""" + idToUse + """\",
      \"reserverID\" : \"1758223867534643\"
    },
    \"-L0IFafxy8xsODXZs65a\" : {
      \"requestData\" : {
        \"creationDate\" : \"""" + today + """\",
        \"endTime\" : \"23:59\",
        \"numParty\" : 5,
        \"partyName\" : \"Uday\",
        \"payment\" : 4,
        \"restaurant\" : {
          \"restaurantAddress\" : \"1151 Westwood Blvd, Los Angeles, CA 90024, USA\",
          \"restaurantCity\" : \"Los Angeles\",
          \"restaurantID\" : \"ChIJiZrskoG8woARfu3e-01wu1w\",
          \"restaurantName\" : \"Ike's Love and Sandwiches\",
          \"restaurantPhoneNumber\" : \"+1 310-208-0770\"
        },
        \"startTime\" : \"20:21\"
      },
      \"requestID\" : \"-L0IFafxy8xsODXZs65a\",
      \"requestState\" : \"Paid\",
      \"requesterID\" : \"""" + idToUse + """\",
      \"reserverID\" : \"1758223867534643\"
    }
  },
  \"users\" : {
    \"10215291050977832\" : {
      \"points\" : 60,
      \"userID\" : \"10215291050977832\"
    },
    \"""" + idToUse + """\" : {
      \"points\" : 50,
      \"userID\" : \"""" + idToUse + """\"
    },
    \"1175385655895739\" : {
      \"points\" : 50,
      \"userID\" : \"1175385655895739\"
    },
    \"1758223867534643\" : {
      \"points\" : 80,
      \"userID\" : \"1758223867534643\"
    }
  }
}"""

file.write(data)
file.close()