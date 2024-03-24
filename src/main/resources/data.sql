INSERT INTO users (username, password, firstname, enabled, meat, fish, vega, vegan) VALUES
('rick@novi.nl', '$2a$12$TCxOXL/GcdpIvCkvsv2tPuyGmSQjUpmZudl9wWCbSVPlDezKft2iG', 'Rick', true, true, true, true, true),
('fred@novi.nl', '$2a$12$vpDT/ULJLbqYJvTazKL.We5aU20rcA2xCYwQvSUfcocEUyuT3Opji', 'Fred', true, true, true, true, false),
('marie@novi.nl', '$2a$12$AY17aw31.vSj7nxTBZebQOjfyNnstuROabKeFjkd0bpa7XOYWCFJi', 'Marie', false, false, false, true, true);

INSERT INTO authorities (username, authority) VALUES
('rick@novi.nl', 'ADMIN'), ('fred@novi.nl', 'USER'), ('marie@novi.nl', 'USER');

INSERT INTO recipes (recipe_id, recipe_name, servings, image_file, creator, hour, minute, meat, fish, vega, vegan) VALUES
(1001, 'Gebakken rijst met knapperige tofu', 4, 'recipe-1001.jpeg', 'marie@novi.nl', 0, 45, false, false, true, true),
(1002, 'Pasta met spinazie en zalm', 4, 'recipe-1002.jpeg', 'fred@novi.nl', 0, 45, false, true, false, false),
(1003, 'Gezonde Havermoutpap met fruit en noten', 2, 'recipe-1003.jpeg', 'rick@novi.nl', 0, 10, false, false, true, true),
(1004, 'Grieke salade', 4, 'recipe-1004.jpeg', 'marie@novi.nl', 0, 30, false, false, true, true),
(1005, 'Pasta met tomatensaus', 4, 'recipe-1005.jpeg', 'rick@novi.nl', 1, 0, true, false, false, false),
(1006, 'Beefburger met hummus en spinazie', 4, 'recipe-1006.jpeg', 'rick@novi.nl', 0, 15, true, false, false, false);

INSERT INTO recipe_tags (recipe_id, tag_name) VALUES
(1001, 'vegan'), (1001, 'glutenvrij'),
(1002, 'vlees'), (1002, 'gemakkelijk'),
(1003, 'ontbijt'), (1003, 'vegetarisch'),
(1004, 'salade'), (1004, 'vegetarisch'), (1004, 'grieks'),
(1005, 'pasta'), (1005, 'vegetarisch'), (1005, 'Italiaans'),
(1006, 'gemakkelijk'), (1006, 'budget'), (1006, 'snel');

INSERT INTO recipe_instructions (recipe_id, step, description) VALUES
(1001, 1, 'Spoel de rijst in een zeef onder koud stromend water en laat uitlekken. Herhaal 3-4 keer totdat het water minder troebel wordt. Zo voorkom je dat de rijstkorrels na het koken aan elkaar gaan plakken.'),
(1001, 2, 'Kook de rijst 3 min. korter dan aangegeven op de verpakking. Verdeel de rijst over een platte schaal en laat 1 uur afkoelen in de koelkast.'),
(1001, 3, 'Snijd ondertussen de sjalot en knoflook grof. Maak de rode peper schoon en snijd het vruchtvlees grof. Doe de sjalot, knoflook, rode peper en een derde van de olie in een hoge beker en pureer met de staafmixer. Snijd de bosui in dunne ringen.'),
(1001, 4, 'Snijd de tofu in blokjes van 1½ cm. Verhit ⅙ van de olie in een koekenpan op hoog vuur en bak de tofu in 10 min. lichtbruin en knapperig. Schep af en toe om. Schep de laatste minuut de gebakken uitjes erdoor.'),
(1001, 5, 'Verhit de rest van de olie in een wok op middelhoog vuur en fruit de sjalotpasta 5 min. onder voortdurend omscheppen.'),
(1001, 6, 'Voeg de rijst, tuinerwten en bosui toe en roerbak alles 3 min. op hoog vuur, tot alle ingrediënten goed gemengd zijn. Voeg de ketjap en sojasaus toe en meng goed.'),
(1001, 7, 'Verdeel de gebakken rijst over diepe borden en verdeel de krokante tofu erover.'),

(1002, 1, 'Verwarm de oven voor op 200 °C. Snijd de kipfilet in de lengte doormidden en bestrooi met peper en eventueel zout. Verhit de grillpan zonder olie of boter en gril de kipfilet 3 min. per kant.'),
(1002, 2, 'Leg de kipfilet op een met bakpapier beklede bakplaat en bak in het midden van de oven in 10 min. gaar.'),
(1002, 3, 'Snijd ondertussen de courgette in de lengte in plakken van ½ cm dik. Snijd de paprika in brede repen. Verhit de olie in een koekenpan en bak de courgette en paprika 5 min. op middelhoog vuur. Breng op smaak met peper en eventueel zout.'),
(1002, 4, 'Verdeel de groenten over 4 borden. Leg de kipfilet erbij.'),

(1003, 1, 'Kook 50 gram havermout in 200 ml melk (of plantaardige melk) op laag vuur gedurende 5 minuten, al roerend.'),
(1003, 2, 'Voeg naar smaak honing, kaneel of vanille extract toe.'),
(1003, 3, 'Snijd ondertussen het fruit in stukjes.'),
(1003, 4, 'Serveer de havermoutpap in een kom en garneer met het fruit en de noten.'),

(1004, 1, 'Snijd de komkommer in plakjes en de tomaten in parten. Snijd de rode ui in dunne ringen. Meng de komkommer, tomaten, rode ui, olijven en feta in een schaal.'),
(1004, 2, 'Meng de olijfolie met de azijn en breng op smaak met peper en eventueel zout. Schep de dressing door de salade.'),
(1004, 3, 'Verdeel de salade over 4 borden.'),

(1005, 1, 'Kook de pasta volgens de aanwijzingen op de verpakking.'),
(1005, 2, 'Snijd ondertussen de knoflook fijn. Verhit de olie in een koekenpan en fruit de knoflook 1 min. Voeg de tomatenblokjes toe en verwarm 5 min. op middelhoog vuur. Breng op smaak met peper en eventueel zout.'),
(1005, 3, 'Verdeel de pasta over 4 borden. Schep de tomatensaus erop.'),

(1006, 1, 'Verhit de olie in een koekenpan en bak de beefburgers op middelhoog vuur in 12 min. bruin en gaar, keer halverwege. '),
(1006, 2, 'Snijd de hamburgerbroodjes doormidden en gril de beide kanten 3 min. in een grillpan. Laat de paprikareepjes uitlekken.'),
(1006, 3, 'Besmeer de broodjes met hummus, verdeel de babyspinazie erover en leg de burgers erop. Leg de gegrilde-paprikareepjes erop en dek af met de andere helft van het broodje.');

INSERT INTO recipe_ingredients (recipe_id, amount, unit, name) VALUES
-- Gebakken rijst met tofu --
(1001, 300, 'gram', 'pandanrijst'),
(1001, 2, 'stuks', 'sjalot'),
(1001, 2, 'tenen', 'knoflook'),
(1001, 1, 'stuks', 'rode peper'),
(1001, 6, 'el', 'zonnebloemolie'),
(1001, 400, 'gram', 'tofu'),
(1001, 100, 'gram', 'gebakken uitjes'),
(1001, 1, 'bos', 'bosui'),
(1001, 200, 'gram', 'tuinerwten'),
(1001, 2, 'el', 'ketjap manis'),
(1001, 2, 'el', 'sojasaus'),

-- Pasta spinazie met zalm --
(1002, 4, 'stuks', 'kipfilet'),
(1002, 4, 'stuks', 'courgette'),
(1002, 4, 'stuks', 'paprika'),
(1002, 4, 'el', 'olijfolie'),

-- Havermout --
(1003, 50, 'gram', 'havermout'),
(1003, 200, 'milliliter', 'melk'),
(1003, 1, 'stuks', 'fruit'),
(1003, 25, 'gram', 'noten'),

-- Griekse salade --
(1004, 1, 'stuks', 'komkommer'),
(1004, 4, 'stuks', 'tomaat'),
(1004, 1, 'stuks', 'rode ui'),
(1004, 150, 'stuks', 'feta'),
(1004, 100, 'gram', 'olijven'),
(1004, 4, 'el', 'olijfolie'),
(1004, 2, 'el', 'witte wijnazijn'),

-- Pasta met tomatensaus --
(1005, 300, 'gram', 'pasta'),
(1005, 2, 'tenen', 'knoflook'),
(1005, 2, 'el', 'olijfolie'),
(1005, 400, 'gram', 'tomatenblokjes'),

-- Beefburgers --
(1006, 1, 'el', 'olijfolie'),
(1006, 4, 'stuks', 'beefburgers'),
(1006, 4, 'stuks', 'hamburgerbroodjes'),
(1006, 310, 'gram', 'rode en gele paprikareepjes'),
(1006, 200, 'gram', 'gegrilde hummus'),
(1006, 100, 'gram', 'babyspinazie');