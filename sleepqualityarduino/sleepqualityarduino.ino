#include <LiquidCrystal.h>
#include <Adafruit_AM2315.h>
#include <DHT.h>
#include "MQ135.h"

LiquidCrystal lcd(8, 9, 4, 5, 6, 7);


#define DHTPIN 30     // what digital pin we're connected to
#define DHTTYPE DHT11
#define btnRIGHT  0
#define btnUP     1
#define btnDOWN   2
#define btnLEFT   3
#define btnSELECT 4
#define btnNONE   5
#define PIN_MQ135 24 // what digital pin we're connected to
#define rzero 229,43

MQ135 mq135_sensor = MQ135(PIN_MQ135);
DHT dht(DHTPIN, DHTTYPE);
int lcd_key     = 0;
int adc_key_in  = 0;
int cases[]       = {0, 1 , 2 , 3 };
int i = 0;
unsigned int days = 0;
unsigned int hours = 0;
unsigned int minutes = 0;
unsigned int seconds = 0;
float x = 0;
int ledPin = 31;
int read_LCD_buttons()
{
 adc_key_in = analogRead(0);      
 if (adc_key_in > 1000) return btnNONE; 
 if (adc_key_in < 50)   return btnRIGHT;  
 if (adc_key_in < 250)  return btnUP; 
 if (adc_key_in < 450)  return btnDOWN; 
 if (adc_key_in < 650)  return btnLEFT; 
 if (adc_key_in < 850)  return btnSELECT;  

 return btnNONE;  
}

void setup()
{
  pinMode(ledPin, OUTPUT);
 lcd.begin(16, 2);
 Serial.begin(9600);             
 delay(2200);
}
 
void loop()
{
  delay(860);
  
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  float resistance = mq135_sensor.getResistance();
 // float rzero = mq135_sensor.getRZero();
  float ppm = mq135_sensor.getPPM();
  x = ppm ;
  if(h>50 || t>35 || x<340){
    digitalWrite(ledPin,HIGH);
  }
  else{
    digitalWrite(ledPin,LOW);
  }
 /* if (isnan(h) || isnan(t) ) {
    Serial.println("Failed to read from DHT sensor!");
    lcd.clear();
    lcd.setCursor(0,0);  //error check if necessary
    lcd.print("Error !");
    return;
  }*/
  
  Serial.print("\n Temperature: ");
  Serial.print(t);
  Serial.print(" *C ");
  Serial.print("\n Humidity: ");
  Serial.print(h);
  Serial.print(" %");
  Serial.print("\n Co2: ");
  Serial.print(x);
  Serial.print("\t PPM");
  Serial.print("\n");
  
  incTime();
  
 lcd_key = read_LCD_buttons();  
 
 switch (lcd_key)               // depending on which button was pushed, we perform an action
 {
   case btnUP:
     {
     lcd.clear();
     i = i+1;
     break;
     }
   case btnDOWN:
     {
     lcd.clear();
     i = i-1;
     break;
     }
 }
 
 lcd.setCursor(0,0);
 lcd.print("Push Up or Down"); 
 lcd.setCursor(0,1);
 
 if (i == -1){
  i = 3;
 }
 else if (i == 4){
  i = 0;
 }

 switch (cases[i]){
  case 0:
    {
      printTime();
      break;
     }
  case 1:
    {
      lcd.print("Humidity: ");
      lcd.print(h);
      lcd.print("%");
      break;
     }
  case 2:
    {
      lcd.print("Temp.: ");
      lcd.print(t);
      lcd.print(" *C");
      break;
     }
  case 3:
    {
      lcd.print("CO2: ");
      lcd.print(x);
      lcd.print("ppm");
      break;
     }
 }
 }
void incTime() {
  // Increase seconds
  seconds++;

  if (seconds == 60) {
    // Reset seconds
    seconds = 0;

    // Increase minutes
    minutes++;

    if (minutes == 60) {
      // Reset minutes
      minutes = 0;

      // Increase hours
      hours++;

      if (hours == 24) {
        // Reset hours
        hours = 0;

        // Increase days
        days++;
      }
    }
  }
}

void printTime() {
  // Set the cursor at the begining of the second row
  lcd.setCursor(0,1);
  char time[17];
  
  sprintf(time, "%02i days %02i:%02i:%02i", days, hours, minutes, seconds);
  lcd.print(time);
}

