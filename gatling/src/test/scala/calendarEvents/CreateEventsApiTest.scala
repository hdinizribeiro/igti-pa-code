package calendarEvents

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

import java.util.Calendar
import java.text.SimpleDateFormat

class CreateEventsApiTest extends Simulation {

 val baseHttpProtocol =
   http.baseUrl("http://freeroom-manager-dev.azurewebsites.net/api")

  val clientId = "c0eff762-9ab1-4da5-8b93-b0f2029fc119"      
  val bearerToken = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkE1Mjg3NzIyMzdFNTI0RTYxMTY5RUM2N0IyQzdFMTZERkY3RTEzQ0MiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJwU2gzSWpmbEpPWVJhZXhuc3NmaGJmOS1FOHcifQ.eyJuYmYiOjE2MTM3NzgwNzMsImV4cCI6MTYxMzc4MTY3MywiaXNzIjoiaHR0cHM6Ly9zdGFnZS1hY2NvdW50cy5mcmVlcm9vbS5pbyIsImF1ZCI6WyJodHRwczovL3N0YWdlLWFjY291bnRzLmZyZWVyb29tLmlvL3Jlc291cmNlcyIsImZyZWVyb29tX2FwaV9yZXNvdXJjZSJdLCJjbGllbnRfaWQiOiJzd2FnZ2VyX2RldiIsImNsaWVudF9mcmVlcm9vbV9pZCI6WyJkNjk1NTIwMS04YjFkLWU4MTEtODBjMi0wMDBkM2FiMTRiMWEiLCJiOGUzNDZhYS04MDlhLTQ4YTktYmIwYy0xMGYxNDk5NGVhZjUiLCJjMGVmZjc2Mi05YWIxLTRkYTUtOGI5My1iMGYyMDI5ZmMxMTkiLCJiZDU3Y2UxZS0yMWQyLTQxZmItOTVhYi1mZDA5YTM3YmJmZmYiLCI2NWU5ZGNiZC0wMzBmLTRmZWMtYjUwYS05MGNlNzkyNTQ0NjIiLCJiMjEzMTg3My03Y2E2LTQzODgtODM5Yy0wZWI0MDRhNWIxOGUiXSwiY2xpZW50X2hhc19hZ2VuZGEiOiJkNjk1NTIwMS04YjFkLWU4MTEtODBjMi0wMDBkM2FiMTRiMWEiLCJjbGllbnRfYmV3ZWxjb21lX2lkIjoiYzc1OGQ5MDEtMTAwOC00NGM3LThjMDktMDU2Y2I5ZTI0MGZlIiwiY2xpZW50X2Rlc2s0bWVfaWQiOiJENjk1NTIwMS04QjFELUU4MTEtODBDMi0wMDBEM0FCMTRCMUEiLCJzdWIiOiIzYTkwMGQxMy00MzJhLTRhYmMtODk1MC00MmI2MGIwYzNjYjUiLCJhdXRoX3RpbWUiOjE2MTM3MzEwNDAsImlkcCI6Imdvb2dsZSIsIm5hbWUiOiJIZW5yaXF1ZSBSaWJlaXJvIiwiZW1haWwiOlsiaGRpbml6QGNpYW5kdC5jb20iLCJoZGluaXpAY2lhbmR0LmNvbSJdLCJtYXRyaWN1bGEiOiJGQUtFLVAwMDMyMyIsInJvbGUiOlsiQWNjb3VudHNBZG1pblJvbGUiLCJkNG1fZ2xvYmFsX2FkbWluIiwiZnJfZ2xvYmFsX2FkbWluIiwiZDRtX3NlcnZpY2VfZGVzayIsImJ3X2dsb2JhbF9hZG1pbiIsImZyX3NlcnZpY2VfZGVzayIsImQ0bV9ncnVwb19ncl9hcGkiXSwic2NvcGUiOlsiZnJlZXJvb21fYXBpX3JlYWRfc2NvcGUiLCJmcmVlcm9vbV9hcGlfd3JpdGVfc2NvcGUiXSwiYW1yIjpbImV4dGVybmFsIl19.L7CYsknT_A1onVQ1JoAWytC2VJwzy-fWNY76DiRqTQXmqaxEpXa33m9fn7H9RIBQK3o6n11NwzLUwFh-qVEQsLRuMY5mxj0uwQbouk_hg3ND2OuhWk-2LrCBehJbny-x1SJKR2FbYskwzmZyNBiV5pYSk4TwYEJtbM186vL6_SmxVJTQhHNm9dp11hQPTk1nJ8M3_inHtOevMdC8AFXS62i3sQMn_73p-0Eu5wc-IiqMB4gX1ClfCeLPmXLi2ELA5KbbsA4A-Y0LgP50huD80CIIbtAV-Adw3IYbKLhs-vzXS4nzQvmBjeHj3ihFNtdEacu-i6wIOAMsvKoP7u3V4Q"
  val start = Calendar.getInstance()
  val end = Calendar.getInstance()

  end.add(Calendar.MINUTE, 30)

  val startISOString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(start.getTime())
  val endISOString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(end.getTime())
 
  val scn = scenario("create-event-scenario")
   .exec(
     http("Create event")
       .post(s"/public/clients/$clientId/events")
       .header("Authorization", bearerToken)
       .body(StringBody(s"""{\"startDate\": \"$startISOString\", \"endDate\": \"$endISOString\",  \"timeZone\": \"America/Sao_Paulo\",  \"rooms\": [    \"02ad910f-7a79-4226-88de-00d63e78ac3b\"  ],  \"email\": \"hdiniz@smartcompany.io\",  \"title\": \"PerfTest\" }"""))   )
 
 setUp(
   scn.inject(     
     constantUsersPerSec(150) during 2.minutes
   ) 
 ).protocols(baseHttpProtocol)  
}