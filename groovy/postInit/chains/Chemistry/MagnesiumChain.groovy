ROASTER = recipemap('roaster')
EBF = recipemap('electric_blast_furnace')
BR = recipemap('batch_reactor')
DISTILLERY = recipemap('distillery')
MIXER = recipemap('mixer')
CRYSTALLIZER = recipemap('crystallizer')
REACTION_FURNACE = recipemap('reaction_furnace')
LCR = recipemap('large_chemical_reactor')
VACUUM_DT = recipemap('vacuum_distillation')
FLBR = recipemap('fluidized_bed_reactor')
DT = recipemap('distillation_tower')

ROASTER.recipeBuilder()
        .inputs(ore('dustMagnesite') * 5)
        .outputs(metaitem('dustMagnesia') * 2)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustDolomite') * 10)
        .outputs(metaitem('dustMagnesia') * 2)
        .outputs(metaitem('dustQuicklime') * 2)
        .fluidOutputs(fluid('carbon_dioxide') * 2000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

EBF.recipeBuilder()
        .inputs(metaitem('dustMagnesia') * 4)
        .inputs(metaitem('dustQuicklime') * 4)
        .inputs(metaitem('dustSilicon') * 1)
        .outputs(metaitem('dustCalciumOrthosilicate') * 7)
        .fluidOutputs(fluid('magnesium') * 288)
        .EUt(30)
        .duration(200)
        .blastFurnaceTemp(1370)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustCalciumOrthosilicate') * 7)
        .outputs(metaitem('dustSiliconDioxide') * 3)
        .outputs(metaitem('dustQuicklime') * 4)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

//IG FARBEN CHLORINATION
class Combustible {
    String name
    String byproduct
    int amount_required
    int duration
    Combustible(name, amount_required, duration, byproduct = 'dustTinyDarkAsh') {
        this.name = name
        this.amount_required = amount_required
        this.duration = duration
        this.byproduct = byproduct
    }
}

def combustibles = [
        new Combustible('dustCarbon', 1, 1),
        new Combustible('gemCoke', 1, 3, 'dustTinyAsh'),
        new Combustible('dustCoke', 1, 3, 'dustTinyAsh'),
        new Combustible('gemAnthracite', 1, 2, 'dustTinyAsh'),
        new Combustible('dustAnthracite', 1, 2, 'dustTinyAsh'),
]

for (combustible in combustibles) {
REACTION_FURNACE.recipeBuilder()
        .inputs(metaitem('dustMagnesia') * 2)
        .inputs(ore(combustible.name) * (combustible.amount_required))
        .fluidInputs(fluid('chlorine') * 2000)
        .outputs(metaitem('dustMagnesiumChloride') * 3)
        .outputs(metaitem(combustible.byproduct))
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()
}

//MPLC CHLORINATION
REACTION_FURNACE.recipeBuilder()
        .inputs(metaitem('dustMagnesite') * 5)
        .fluidInputs(fluid('carbon_monoxide') * 1000)
        .fluidInputs(fluid('chlorine') * 2000)
        .outputs(metaitem('dustMagnesiumChloride') * 3)
        .fluidOutputs(fluid('carbon_dioxide') * 2000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

LCR.recipeBuilder()
        .inputs(metaitem('dustMagnesite') * 5)
        .fluidInputs(fluid('hydrochloric_acid') * 2000)
        .fluidOutputs(fluid('magnesium_chloride_solution') * 2000)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustMagnesia') * 2)
        .fluidInputs(fluid('water') * 1000)
        .outputs(metaitem('dustMagnesiumHydroxide') * 5)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustMagnesiumHydroxide') * 5)
        .fluidInputs(fluid('hydrochloric_acid') * 2000)
        .fluidOutputs(fluid('magnesium_chloride_solution') * 2000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('magnesium_chloride_solution') * 2000)
        .outputs(metaitem('dustMagnesiumChloride') * 3)
        .fluidOutputs(fluid('water') * 2000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(metaitem('dustMagnesiumSulfate') * 6)
        .outputs(metaitem('dustMagnesia') * 2)
        .fluidOutputs(fluid('sulfur_trioxide') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(metaitem('dustKieserite') * 7)
        .outputs(metaitem('dustMagnesiumSulfate') * 6)
        .fluidOutputs(fluid('steam') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustLangbeinite') * 24)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('langbeinite_leach') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('langbeinite_leach') * 1000)
        .outputs(metaitem('dustMagnesiumSulfate') * 12)
        .fluidOutputs(fluid('potassium_sulfate_solution') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('potassium_sulfate_solution') * 1000)
        .outputs(metaitem('dustPotassiumSulfate') * 7)
        .fluidOutputs(fluid('water') * 1000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustPolyhalite') * 32)
        .fluidInputs(fluid('water') * 1000)
        .outputs(metaitem('dustCalciumSulfate') * 12)
        .fluidOutputs(fluid('polyhalite_leach') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('polyhalite_leach') * 1000)
        .outputs(metaitem('dustMagnesiumSulfate') * 6)
        .fluidOutputs(fluid('potassium_sulfate_solution') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

//QMC PROCESS
BR.recipeBuilder()
        .fluidInputs(fluid('magnesium_chloride_solution') * 1000)
        .fluidInputs(fluid('ethylene_glycol') * 3000)
        .fluidOutputs(fluid('hydrated_magnesium_chloride_glycolate') * 1000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

VACUUM_DT.recipeBuilder()
        .fluidInputs(fluid('hydrated_magnesium_chloride_glycolate') * 1000)
        .fluidOutputs(fluid('magnesium_chloride_glycolate') * 1000)
        .fluidOutputs(fluid('water') * 2000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('magnesium_chloride_glycolate') * 1000)
        .fluidInputs(fluid('ammonia') * 6000)
        .outputs(metaitem('dustMagnesiumChlorideAmmoniate') * 9)
        .fluidOutputs(fluid('impure_ethylene_glycol') * 3000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('impure_ethylene_glycol') * 3000)
        .chancedOutput(metaitem('dustCalciumChloride') * 3, 500, 0)
        .fluidOutputs(fluid('ethylene_glycol') * 2900)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('ammonia') * 500)
        .fluidInputs(fluid('methanol') * 1000)
        .fluidOutputs(fluid('saturated_ammoniacal_methanol') * 1000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustMagnesiumChlorideAmmoniate') * 9)
        .fluidInputs(fluid('saturated_ammoniacal_methanol') * 1000)
        .outputs(metaitem('dustWashedMagnesiumChlorideAmmoniate') * 9)
        .fluidOutputs(fluid('impure_saturated_ammoniacal_methanol') * 1000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

DT.recipeBuilder()
        .fluidInputs(fluid('impure_saturated_ammoniacal_methanol') * 1000)
        .fluidOutputs(fluid('ethylene_glycol') * 100)
        .fluidOutputs(fluid('methanol') * 1000)
        .fluidOutputs(fluid('ammonia') * 500)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

FLBR.recipeBuilder()
        .inputs(metaitem('dustMagnesiumChlorideAmmoniate') * 9)
        .outputs(metaitem('dustPurifiedMagnesiumChloride') * 3)
        .fluidOutputs(fluid('ammonia') * 6000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()





        